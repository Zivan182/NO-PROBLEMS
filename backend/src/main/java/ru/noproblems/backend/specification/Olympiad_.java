package ru.noproblems.backend.specification;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.noproblems.backend.data.Olympiad;

@StaticMetamodel(Olympiad.class)
public class Olympiad_ {

    public static volatile SingularAttribute<Olympiad, Long> id;
    public static volatile SingularAttribute<Olympiad, String> name;

}
