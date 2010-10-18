package org.shortbrain.util.tree;

import java.util.ArrayList;
import java.util.Collection;

public class Tree<T extends Comparable<T>> {

  private Node<T> rootElement;

  public Tree() {
    super();
  }

  /**
   * @return the rootElement
   */
  public Node<T> getRootElement() {
    return rootElement;
  }

  /**
   * @param rootElement
   *          the rootElement to set
   */
  public void setRootElement(Node<T> rootElement) {
    this.rootElement = rootElement;
  }

  public Collection<Node<T>> toList() {
    Collection<Node<T>> list = new ArrayList<Node<T>>();
    walk(rootElement, list);
    return list;
  }

  public String toString() {
    return toList().toString();
  }

  private void walk(Node<T> element, Collection<Node<T>> list) {
    list.add(element);
    for (Node<T> data : element.getChildren()) {
      walk(data, list);
    }
  }

  public Tree<T> union(Tree<T> t) {
    Tree<T> ret = this.clone();
    union(ret.getRootElement(), t.getRootElement());
    return ret;
  }

  private void union(Node<T> n, Node<T> n1) {
    for (Node<T> n1c : n1.getChildren()) {
      if (n.getChildren().contains(n1c)) {
        int i = n.getChildren().indexOf(n1c);
        union(n.getChildren().get(i), n1c);
      } else {
        n.addChild(n1c);
      }
    }
  }

  private void cloneNode(Node<T> newNode, Node<T> origNode) {
    // newNode = new Node<T>(origNode); // BAD, isn't it ?
    for (Node<T> origChild : origNode.getChildren()) {
      Node<T> newChild = new Node<T>(origChild);
      newNode.addChild(newChild);
      cloneNode(newChild, origChild);
    }
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#clone()
   */
  @Override
  public Tree<T> clone() {
    Tree<T> ret = new Tree<T>();
    Node<T> root = new Node<T>(this.rootElement);
    cloneNode(root, this.rootElement);
    ret.setRootElement(root);
    return ret;
  }

}
