package treesPrograms;

public class GetLevel {

    public static void main(String[] args) {

        Node root = Input.treeInput();
        int level = returnLevel(root, 11, 1);
        if (level != -1) {
            System.out.println("Level is - " + level);
        } else {
            System.out.println("Nope, not present");
        }

    }

    private static int returnLevel(Node root, int target, int level) {

        if (root == null) {
            return -1;
        }
        if (root.info == target) {
            return level;
        }

        int downLevel = returnLevel(root.left, target, level + 1);
        if (downLevel != -1) {
            return downLevel;
        }

        downLevel = returnLevel(root.right, target, level + 1);
        if (downLevel != -1) {
            return downLevel;
        }

        return -1;

    }

}