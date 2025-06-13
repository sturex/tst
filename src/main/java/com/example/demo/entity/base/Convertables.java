package com.example.demo.entity.base;

import java.util.Collection;
import java.util.List;

public enum Convertables {
    ;

    public static <M> List<M> convert(Collection<? extends Convertable<M>> convertables) {
        return convertables.parallelStream().map(Convertable::convert).toList();
    }
}
