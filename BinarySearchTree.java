public class BinarySearchTree<E extends Comparable<E>>{
    private Node<E> root;
    private int passes = 0;

    public BinarySearchTree(){
        root = null;
    }
    public void add(E data){
        passes = 0;
        if(root == null){
            root = new Node<E>(data);
        }
        else{
            add(data, root);
        }
    }

    private void add(E data, Node<E> current){
        passes++;
        if(current.getLeft() == null && data.compareTo(current.get()) < 0){
            current.setLeft(new Node<E>(data));
            current.getLeft().setParent(current);
        }
        else if(current.getRight() == null && data.compareTo(current.get()) > 0){
            current.setRight(new Node<E>(data));
            current.getRight().setParent(current);
        }
        else{
            if(data.compareTo(current.get()) < 0){
                current = current.getLeft();
            }
            else if(data.compareTo(current.get()) > 0){
                current = current.getRight();
            }
            add(data, current);
        }
    }

    public E get(E data){
        passes = 0;
        return get(data, root);
    }

    private E get(E data, Node<E> current){
        passes++;
        if(current.get().equals(data)){
            return current.get();
        }
        else if(current.get().compareTo(data) < 0){
            if(current.getRight() != null)
                return get(data, current.getRight());
            else
                return null;
        }
        else if(current.get().compareTo(data) > 0){
            if(current.getLeft() != null)
                return get(data, current.getLeft());

            else
                return null;
        }
        return null;
    }

    public int getPasses(){
        return passes;
    }

    public String toString(){
        return inOrderString(root);
    }
    public String inOrderString(Node<E> current){
        String returnString = "";
        if(current != null){
            returnString += inOrderString(current.getLeft()) + "\n";
            returnString += current.get() + "\n";
            returnString += inOrderString(current.getRight());
        }
        return returnString;
    }
    public boolean contains(E data){
        return contains(data, root);
    }
    private boolean contains(E data, Node<E> current){
        if(data.equals(current.get())){
            return true;
        }
        else{
            if(data.compareTo(current.get()) > 0){
                if(current.getRight() == null){
                    return false;
                }
                current = current.getRight();
            }
            else if(data.compareTo(current.get()) < 0){
                if(current.getLeft() == null){
                    return false;
                }
                current = current.getLeft();
            }
            return contains(data, current);
        }
    }

    public void remove(E data){
        if(contains(data)){
            remove(data, root);
        }
    }

    private void remove(E data, Node<E> current){
        if(current.get().equals(data)){
             if(current.getLeft() == null && current.getRight() == null){//if has no children
                if(data.compareTo(current.getParent().get()) > 0){
                    current.getParent().setRight(null);
                }
                else if(data.compareTo(current.getParent().get()) < 0){
                    current.getParent().setLeft(null);
                }
            }
            else if(current.getRight() != null){//if has children
                E temp = getLowest(current.getRight());
                remove(temp);
                current.set(temp);
            }
        }
        else{
            //call remove passing in new current and parent
            if(data.compareTo(current.get()) > 0){
                current = current.getRight();
            }
            else if(data.compareTo(current.get()) < 0){
                current = current.getLeft();
            }
            if(current != null)
            remove(data, current);
   
            else
            return;
        }
    }
    private E getLowest(Node<E> current){
        Node<E> temp = current;
        if(current.getLeft() != null){
            current = current.getLeft();
            return getLowest(current);
        }else{
            return temp.get();
        }
    }
     public String toStringPreOrder(){
        return toStringPreOrder(root);
    }
    private String toStringPreOrder(Node<E> current){
        String returnString = "";
        returnString += current.get() + " ";
        if(current.getLeft() != null){
            returnString += toStringPreOrder(current.getLeft()) + " ";
        }
        if(current.getRight() != null){
            returnString += toStringPreOrder(current.getRight()) + " ";
        }
        return returnString;
    }

}
   
  