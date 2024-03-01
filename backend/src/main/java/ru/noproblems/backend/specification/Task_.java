package ru.noproblems.backend.specification;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import ru.noproblems.backend.data.Olympiad;
import ru.noproblems.backend.data.Task;
import ru.noproblems.backend.data.Topic;
import ru.noproblems.backend.data.User;

@StaticMetamodel(Task.class)
public class Task_ {

    public static volatile SingularAttribute<Task, Long> id;
    public static volatile SingularAttribute<Task, String> condition;
    public static volatile SingularAttribute<Task, String> solution;
    public static volatile SingularAttribute<Task, Topic> topic;
    public static volatile SingularAttribute<Task, Olympiad> olympiad;

    public static volatile SingularAttribute<Task, Long> complexity;
    public static volatile SingularAttribute<Task, Long> year;
    public static volatile SingularAttribute<Task, Long> grade;

    public static volatile SingularAttribute<Task, String> author;
    public static volatile SingularAttribute<Task, User> whoAdded;

    


}
