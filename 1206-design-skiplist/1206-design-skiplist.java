class Skiplist {

    private static final int MAX_LEVEL = 16;

    private class Node {
        int val;
        Node[] next;

        Node(int val) {
            this.val = val;
            this.next = new Node[MAX_LEVEL];
        }
    }

    private Node head;
    private int level;

    public Skiplist() {
        head = new Node(-1);
        level = 1;
    }

    // Search for target
    public boolean search(int target) {

        Node current = head;

        for (int i = level - 1; i >= 0; i--) {

            while (current.next[i] != null &&
                   current.next[i].val < target) {

                current = current.next[i];
            }
        }

        current = current.next[0];

        return current != null && current.val == target;
    }

    // Add a number
    public void add(int num) {

        Node[] update = new Node[MAX_LEVEL];

        Node current = head;

        for (int i = level - 1; i >= 0; i--) {

            while (current.next[i] != null &&
                   current.next[i].val < num) {

                current = current.next[i];
            }

            update[i] = current;
        }

        int newLevel = randomLevel();

        if (newLevel > level) {

            for (int i = level; i < newLevel; i++) {
                update[i] = head;
            }

            level = newLevel;
        }

        Node newNode = new Node(num);

        for (int i = 0; i < newLevel; i++) {

            newNode.next[i] = update[i].next[i];
            update[i].next[i] = newNode;
        }
    }

    // Erase a number
    public boolean erase(int num) {

        Node[] update = new Node[MAX_LEVEL];

        Node current = head;

        for (int i = level - 1; i >= 0; i--) {

            while (current.next[i] != null &&
                   current.next[i].val < num) {

                current = current.next[i];
            }

            update[i] = current;
        }

        current = current.next[0];

        if (current == null || current.val != num) {
            return false;
        }

        for (int i = 0; i < level; i++) {

            if (update[i].next[i] != current) {
                break;
            }

            update[i].next[i] = current.next[i];
        }

        // Remove empty levels
        while (level > 1 && head.next[level - 1] == null) {
            level--;
        }

        return true;
    }

    // Generate random level
    private int randomLevel() {

        int lvl = 1;

        while (Math.random() < 0.5 && lvl < MAX_LEVEL) {
            lvl++;
        }

        return lvl;
    }
}