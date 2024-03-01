package ru.noproblems.backend.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.noproblems.backend.data.Task;


public class TaskSpecifications {
    public static Specification<Task> yearNotLess(Long year) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.greaterThanOrEqualTo(root.get(Task_.year), year);
            }
        };
    }

    public static Specification<Task> yearNotGreater(Long year) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.lessThanOrEqualTo(root.get(Task_.year), year);
            }
        };
    }

    public static Specification<Task> complexityNotLess(Long complexity) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.greaterThanOrEqualTo(root.get("complexity"), complexity);
            }
        };
    }

    public static Specification<Task> complexityNotGreater(Long complexity) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.lessThanOrEqualTo(root.get(Task_.complexity), complexity);
            }
        };
    }

    public static Specification<Task> topicIn(List<String> topics) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return root.get(Task_.topic).get(Topic_.name).in(topics);
            }
        };
    }

    public static Specification<Task> olympiadIn(List<String> olympiads) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return root.get(Task_.olympiad).get(Olympiad_.name).in(olympiads);
            }
        };
    }

    public static Specification<Task> conditionContains(String search) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.like(root.get(Task_.condition), "%${search}%");
            }
        };
    }

    public static Specification<Task> wasAddedByAdminOr(Long userId) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                var pred1 = cb.equal(root.get(Task_.whoAdded).get(User_.isAdmin), true);
                if  (userId == null) {
                    return pred1;
                }
                var pred2 = cb.equal(root.get(Task_.whoAdded).get(User_.id), userId);
                return cb.or(pred1, pred2);
            }
        };
    }

    public static Specification<Task> LikedBy(Long userId, Boolean cond) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return (cond 
                        ? cb.isMember(userId, root.get("likeReactions").get("user").get("id"))
                        : cb.isNotMember(userId, root.get("likeReactions").get("user").get("id")));
            }
        };
    }


    public static Specification<Task> SolvedBy(Long userId, Boolean cond) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return (cond 
                        ? cb.isMember(userId, root.get("solveReactions").get("user").get("id"))
                        : cb.isNotMember(userId, root.get("solveReactions").get("user").get("id")));
            }
        };
    }

    public static Specification<Task> AddedBy(Long userId, Boolean cond) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return (cond 
                        ? cb.equal(root.get("whoAdded").get("id"), userId)
                        : cb.notEqual(root.get("whoAdded").get("id"), userId));
            }
        };
    }
}
