package model;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase que implementa una lista enlazada simple genérica.
 * Permite almacenar elementos en una estructura dinámica, donde los nodos están conectados linealmente.
 *
 * @param <T> El tipo de datos que almacenará la lista.
 */
public class ListaEnlazadaSimple<T> implements Iterable<T> {

    private Node<T> head;
    private int size;

    /**
     * Constructor que inicializa una lista enlazada simple vacía.
     */
    public ListaEnlazadaSimple() {
        head = null;
        size = 0;
    }

    /**
     * Agrega un elemento al final de la lista.
     *
     * @param data El elemento a agregar.
     */
    public void addLast(T data) {
        if (head == null) {
            head = new Node<>(data);
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = new Node<>(data);
        }
        size++;
    }

    /**
     * Elimina el primer nodo que contiene el dato dado.
     *
     * @param data El dato a eliminar.
     * @return {@code true} si se eliminó el dato, {@code false} si no se encontró.
     */
    public boolean remove(T data) {
        if (head == null) return false;
        if (head.data.equals(data)) {
            head = head.next;
            size--;
            return true;
        }
        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }
        if (current.next == null) {
            return false;
        } else {
            current.next = current.next.next;
            size--;
            return true;
        }
    }

    /**
     * Devuelve el dato en la posición indicada.
     *
     * @param index El índice del elemento a obtener (comienza en 0).
     * @return El dato en la posición indicada.
     * @throws IndexOutOfBoundsException Si el índice no es válido.
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice: " + index);
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    /**
     * Ordena los elementos de la lista utilizando un Comparator.
     *
     * @param comparator El comparador que define el orden de los elementos.
     */
    public void ordenar(Comparator<T> comparator) {
        if (size <= 1 || head == null) {
            // No hacer nada si la lista está vacía o tiene un solo elemento
            return;
        }

        boolean huboIntercambios;
        do {
            Node<T> current = head;
            Node<T> next = head.next;
            huboIntercambios = false;

            while (next != null) {
                // Usar el comparador para determinar si intercambiar elementos
                if (comparator.compare(current.data, next.data) > 0) {
                    // Intercambiamos los datos de los nodos
                    T temp = current.data;
                    current.data = next.data;
                    next.data = temp;

                    huboIntercambios = true;
                }

                current = next;
                next = next.next;
            }
        } while (huboIntercambios);
    }

    /**
     * Devuelve el tamaño de la lista.
     *
     * @return El número de elementos en la lista.
     */
    public int size() {
        return size;
    }

    /**
     * Verifica si la lista está vacía.
     *
     * @return {@code true} si la lista está vacía, {@code false} en caso contrario.
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Iterador para recorrer los elementos de la lista.
     *
     * @return Un iterador para los elementos almacenados en la lista.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Node<T> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = current.data;
                current = current.next;
                return data;
            }
        };
    }

    /**
     * Clase interna que representa un nodo en la lista.
     *
     * @param <E> El tipo de datos almacenado en el nodo.
     */
    private static class Node<E> {
        E data;
        Node<E> next;

        /**
         * Constructor que inicializa un nodo con un dato.
         *
         * @param data El dato almacenado en el nodo.
         */
        Node(E data) {
            this.data = data;
            this.next = null;
        }
    }
}