package org.shortbrain.util.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: Documenter cette classe
 * 
 * @author vincent
 * 
 * @param <T>
 */
public class Node<T extends Comparable<T>> implements Comparable<T> {

  public T data;
  public List<Node<T>> children;

  public Node(T data) {
    super();
    setData(data);
  }

  /**
   * Sort of clone ! But we do not want to copy Children.
   * 
   * @param node
   */
  public Node(Node<T> node) {
    this(node.getData());
  }

  public List<Node<T>> getChildren() {
    if (this.children == null) {
      return new ArrayList<Node<T>>();
    }
    return this.children;
  }

  public void setChildren(List<Node<T>> children) {
    this.children = children;
  }

  public int getNumberOfChildren() {
    if (this.children == null) {
      return 0;
    }
    return this.children.size();
  }

  public int getRecursiveNumberOfChildren() {
    int nb = 1;
    for (Node<T> d : getChildren()) {
      nb += d.getRecursiveNumberOfChildren();
    }
    return nb;
  }

  public void addChild(Node<T> child) {
    if (children == null) {
      this.children = new ArrayList<Node<T>>();
    }
    this.children.add(child);
  }

  public void insertChildAt(int index, Node<T> child) throws IndexOutOfBoundsException {
    if (index == getNumberOfChildren()) {
      addChild(child);
    } else {
      children.get(index); // throw exception if it has to.. and does not execute the following
      children.add(index, child);
    }
  }

  public void removeChildAt(int index) throws IndexOutOfBoundsException {
    children.remove(index);
  }

  /**
   * @return the data
   */
  public T getData() {
    return data;
  }

  /**
   * @param data
   *          the data to set
   */
  public void setData(T data) {
    this.data = data;
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("{").append(getData().toString()).append(",[");
    int i = 0;
    for (Node<T> e : getChildren()) {
      if (i > 0) {
        sb.append(",");
      }
      sb.append(e.toString());
      i++;
    }
    sb.append("]").append("}");
    return sb.toString();
  }

  public String toPrettyString() {
    return toPrettyString(">");
  }

  public String toPrettyString(String prefix) {
    StringBuilder sb = new StringBuilder();
    sb.append(prefix).append(getData().toString()).append("\n");
    int i = 0;
    for (Node<T> e : getChildren()) {
      sb.append(e.toPrettyString(prefix + ">"));
      i++;
    }
    return sb.toString();
  }

  @Override
  public int compareTo(T o) {
    return this.data.compareTo(o);
  }

  /*
   * (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Node<?>) {
      return this.getData().equals(((Node<?>) obj).getData());
    }
    return super.equals(obj);
  }

}
