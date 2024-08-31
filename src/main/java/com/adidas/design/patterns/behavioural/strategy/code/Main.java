package com.adidas.design.patterns.behavioural.strategy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Main {
    public static void main(String args[]) {
        Animal dog = new Dog();


        dog.setName("Tommy");
        dog.setWeight("25 lbs");

        log.info("dog : {} ", dog);

        log.info("------------------");

        Animal bird = new Bird();

        bird.setName("Tweety");
        bird.setWeight("5 lbs");
        log.info("bird : {}", bird);

        log.info("------------------");

        /**
         * all of a sudden let's say we extend the feature of flying to dog
         *
         */

        dog.setFlyingType(new CanFly());
        log.info("dog : {}", dog);

        /*testSomething(dog);
        testSomething(new Dog());

        testSomething(new Bird());

        testSomething();

    }

    /*private static void testSomething(Animal animal) {
        if(animal instanceof Dog)
            log.info("this is a type of dog:", animal);
        else
            log.info("this is a different type of animal : ");


    }*/

  /*  public static void testSomething(){
        List<Student> students = List.of(
                new Student("Alice", 10),
                new Student("Bob", 12),
                new Student("Charlie", 10),
                new Student("David", 11)
        );

        Map<Integer, List<Student>> studentsByGrade = students.stream()
                .collect(Collectors.groupingBy(Student::getAge));
        Collection<List<Student>> values = studentsByGrade.values();
        log.info("test something : {}", values);
    }

    @Data
    @AllArgsConstructor
    private static class Student {
        private String name;
        private int age;

    }
*/

    }
}
