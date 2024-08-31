package com.adidas.design.patterns.behavioural.visitor.code;

import com.adidas.design.patterns.behavioural.visitor.code.Visitor;

public interface Visitable {

    public double accept(Visitor visitor);
}
