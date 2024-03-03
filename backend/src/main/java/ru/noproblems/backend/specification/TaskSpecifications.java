package ru.noproblems.backend.specification;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import ru.noproblems.backend.data.LikeReaction;
import ru.noproblems.backend.data.SolveReaction;
import ru.noproblems.backend.data.Task;


public class TaskSpecifications {
    public static Specification<Task> yearNotLess(Long year) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.greaterThanOrEqualTo(root.get("year"), year);
            }
        };
    }

    public static Specification<Task> yearNotGreater(Long year) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.lessThanOrEqualTo(root.get("year"), year);
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
                return cb.lessThanOrEqualTo(root.get("complexity"), complexity);
            }
        };
    }

    public static Specification<Task> topicIn(List<String> topics) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return root.get("topic").get("name").in(topics);
            }
        };
    }

    public static Specification<Task> olympiadIn(List<String> olympiads) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return root.get("olympiad").get("name").in(olympiads);
            }
        };
    }

    public static Specification<Task> conditionContains(String search) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.like(root.get("condition"), "%${search}%");
            }
        };
    }

    public static Specification<Task> wasAddedByAdminOr(Long userId) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                var pred1 = cb.equal(root.get("whoAdded").get("isAdmin"), true);
                if  (userId == null) {
                    return pred1;
                }
                var pred2 = cb.equal(root.get("whoAdded").get("id"), userId);
                return cb.or(pred1, pred2);
            }
        };
    }

    public static Specification<Task> LikedBy(Long userId) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join<Task, LikeReaction> likesJoin = root.join("likeReactions");
                return cb.equal(likesJoin.get("user").get("id"), userId);

                // return (cond 
                //         ? cb.equal(likesJoin.get("user").get("id"), userId)
                //         : cb.notEqual(likesJoin.get("user").get("id"), userId));
            }
        };
    }

    public static Specification<Task> NotLikedBy(Long userId) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join<Task, LikeReaction> likesJoin = root.join("likeReactions");
                // var pred1 = cb.isNull(root.get("likeReactions"));
                var pred2 = cb.isEmpty(root.get("likeReactions"));
                var pred3 = cb.equal(likesJoin.get("user").get("id"), userId).not();
                return cb.or(pred2,  pred3);
                // return (cond 
                //         ? cb.equal(likesJoin.get("user").get("id"), userId)
                //         : cb.notEqual(likesJoin.get("user").get("id"), userId));
            }
        };
    }


    public static Specification<Task> SolvedBy(Long userId, Boolean cond) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join<Task, SolveReaction> solvesJoin = root.join("solveReactions");
                return cb.equal(solvesJoin.get("user").get("id"), userId);
                // return (cond 
                //         ? cb.isMember(userId, solvesJoin.get("user").get("id"))
                //         : cb.isNotMember(userId, solvesJoin.get("user").get("id")));
            }
        };
    }

    public static Specification<Task> AddedBy(Long userId, Boolean cond) {
        return new Specification<Task>() {
            public Predicate toPredicate(Root<Task> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                // return cb.equal(root.get("whoAdded").get("id"), userId); 
                return (cond 
                        ? cb.equal(root.get("whoAdded").get("id"), userId)
                        : cb.notEqual(root.get("whoAdded").get("id"), userId));
            }
        };
    }
}
