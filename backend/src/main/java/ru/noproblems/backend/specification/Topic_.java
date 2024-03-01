package ru.noproblems.backend.specification;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.noproblems.backend.data.Topic;

@StaticMetamodel(Topic.class)
public class Topic_ {

    public static volatile SingularAttribute<Topic, Long> id;
    public static volatile SingularAttribute<Topic, String> name;

}
