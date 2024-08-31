package com.adidas.multithreading.performanceoptimization.throughput;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.*;

/**
 * We are creating a Http server - Search and Count Word that serves many Http requests send to it
 * <p>
 * What we are doing is Searching a particular word(the number of times the word is present) in the text that is provided in the paragraph in the resource and send it back to the user
 * <p>
 * What we are trying to understand/achieve from this example is counting the number of requests(tasks) the server can serve us per second and try to analyse and calculate the same
 * <p>
 * Also please go through this link for more understanding details https://gemini.google.com/app/302c881ee413dc07
 */
public class ThroughputHttpServer {
    private static final String INPUT_FILE = "C:\\Users\\nathnil\\Downloads\\design-patterns\\design-patterns\\src\\main\\java\\com\\adidas\\multithreading\\performanceoptimization\\throughput\\resources\\testingfiles\\war_and_peace.txt";
    private static final int NUMBER_OF_THREADS = 1;

    public static void main(String args[]) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));
        startServer(text);
    }

    private static void startServer(String text) throws IOException {
        /**
         * we are creating a server that is exposed on port 8000 and backlog is 0 which means
         *
         * A maximum backlog can also be specified. This is the maximum number of
         *      * queued incoming connections to allow on the listening socket.
         *      * Queued TCP connections exceeding this limit may be rejected by the TCP
         *      * implementation. The {@code HttpServer} is acquired from the currently
         *      * installed {@link HttpServerProvider}
         *      *
         *      * @param addr the address to listen on, if {@code null} then
         *      *             {@link #bind(InetSocketAddress, int)} must be called to set
         *      *             the address
         *      * @param backlog the socket backlog. If this value is less than or equal to zero,
         *      *                then a system default value is used
         *
         *      now this is kept at '0' because we want all the requests to be queued to the ThreadPool queue instead
         */
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        /**
         * using the server object we created the handler method like we have in the controllers where a particular endpoint in our case
         * "../search" has a controller(handler) method that handles all the incoming httprequests to this particular endpoint and send the httpResponse
         *
         *
         */
        server.createContext("/search", new WordCountHandler(text));

        /**
         * all incoming httpRequest will be send to this ThreadPool we are setting this executor to the server
         */
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        server.setExecutor(executor);
        /**
         * once the server is started it will listen to all HttpRequests on port 8000
         */
        server.start();
    }

    private static class WordCountHandler implements HttpHandler {
        /**
         * the book object containing the book
         */
        private String text;

        public WordCountHandler(String text) {
            this.text = text;
        }

        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            String query = httpExchange.getRequestURI().getQuery();
            String[] keyValue = query.split("=");
            /**
             * ../search?word=ok
             * so the
             * action = word
             * wordValue = ok
             */
            String action = keyValue[0];
            String wordValue = keyValue[1];
            if (!action.equals("word")) {
                httpExchange.sendResponseHeaders(400, 0);
                return;
            }

            long count = countWord(wordValue);
            byte[] response = Long.toString(count).getBytes();
            httpExchange.sendResponseHeaders(200, response.length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(response);
            outputStream.close();

        }

        private long countWord(String wordValue) {
            long count = 0;
            int index = 0;
            while (index >= 0) {
                index = text.indexOf(wordValue, index);
                if (index >= 0) {
                    index++;
                    count++;
                }
            }
            return count;
        }
    }


}
