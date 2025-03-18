import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class User {
    int userId;
    String name;
    int age;
    List<Integer> friendIds;
    User next;

    User(int userId, String name, int age) {
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.friendIds = new ArrayList<>();
        this.next = null;
    }
}

class SocialMedia {
    private User head;
    private HashMap<Integer, User> userMap = new HashMap<>();

    public void addUser(int userId, String name, int age) {
        User newUser = new User(userId, name, age);
        newUser.next = head;
        head = newUser;
        userMap.put(userId, newUser);
    }

    public void addFriend(int userId1, int userId2) {
        if (userMap.containsKey(userId1) && userMap.containsKey(userId2)) {
            userMap.get(userId1).friendIds.add(userId2);
            userMap.get(userId2).friendIds.add(userId1);
        }
    }

    public void removeFriend(int userId1, int userId2) {
        if (userMap.containsKey(userId1) && userMap.containsKey(userId2)) {
            userMap.get(userId1).friendIds.remove(Integer.valueOf(userId2));
            userMap.get(userId2).friendIds.remove(Integer.valueOf(userId1));
        }
    }

    public List<Integer> findMutualFriends(int userId1, int userId2) {
        List<Integer> mutualFriends = new ArrayList<>();
        if (userMap.containsKey(userId1) && userMap.containsKey(userId2)) {
            for (int friend : userMap.get(userId1).friendIds) {
                if (userMap.get(userId2).friendIds.contains(friend)) {
                    mutualFriends.add(friend);
                }
            }
        }
        return mutualFriends;
    }

    public void displayFriends(int userId) {
        if (userMap.containsKey(userId)) {
            System.out.println("Friends of " + userId + ": " + userMap.get(userId).friendIds);
        }
    }

    public void searchUser(int userId) {
        if (userMap.containsKey(userId)) {
            User user = userMap.get(userId);
            System.out.println("User Found: " + user.name + ", Age: " + user.age);
        } else {
            System.out.println("User not found.");
        }
    }

    public int countFriends(int userId) {
        return userMap.containsKey(userId) ? userMap.get(userId).friendIds.size() : 0;
    }
}

public class SocialMediaFriends {
    public static void main(String[] args) {
        SocialMedia sm = new SocialMedia();
        sm.addUser(1, "Alice", 25);
        sm.addUser(2, "Bob", 23);
        sm.addUser(3, "Charlie", 30);

        sm.addFriend(1, 2);
        sm.addFriend(1, 3);

        sm.displayFriends(1);
        sm.displayFriends(2);

        System.out.println("Mutual Friends of 1 and 2: " + sm.findMutualFriends(1, 2));
        
        sm.removeFriend(1, 2);
        sm.displayFriends(1);
    }
}
