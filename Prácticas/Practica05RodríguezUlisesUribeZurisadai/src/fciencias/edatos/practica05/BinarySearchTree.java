package fciencias.edatos.practica05;

import java.util.Scanner;

/**
* Define las operaciones sobre un árbol
* binario de búsqueda.
* @author Ulises Rodríguez García
* @author Zurisadai Uribe García
* @version 24 Noviembre 2021.
* @since Estructuras de Datos 2022-1. Práctica 05.
*/
public class BinarySearchTree<K extends Comparable<K>, T> implements TDABinarySearchTree<K, T>	{
	
	/**
	 * Nodo para un árbol binario de búsqueda.
	 */
	public class BinaryNode{

		/** Clave. */
		public K key;

		/** Elemento. */
		public T element;

		/** Padre del nodo. */
		public BinaryNode parent;

		/** Hijo izquierdo. */
		public BinaryNode left;

		/** Hijo derecho. */
		public BinaryNode rigth;

		/**
		 * Crea un nuevo nodo.
		 * @param key la clave.
		 * @param element el elemento a almacenar.
		 * @param parent el padre del nodo.
		 */
		public BinaryNode(K key, T element, BinaryNode parent){
			this.key = key;
			this.element = element;
			this.parent = parent;
		}
	}

	/** Root */
	private BinaryNode root;

	@Override
	public T retrieve(K k){
		BinaryNode node = retrieve(root, k);
		if(node == null)
			return null;
		return node.element;
	}

	private BinaryNode retrieve(BinaryNode actual, K k){
		// No se encuentra el elemento
		if(actual == null)
			return null;

		// Si encontramos el elemento
		if(actual.key.compareTo(k) == 0)
			return actual;

		// Comparamos los elementos
		if(k.compareTo(actual.key) < 0){ // Verificamos en la izquierda
			return retrieve(actual.left, k);
		} else { // Verificar en la derecha
			return retrieve(actual.rigth, k);
		}
	}

	/**
	* Inserta un nuevo elemento al árbol.
	* @param e el elemento a ingresar.
	* @param k la clave del elemento a ingresar.
	*/
    @Override
	public void insert(T e, K k){
        if (isEmpty()){
        	root = new BinaryNode(k, e, null);
			return;
		}
        insert(root, e, k);
	}
	
	private void insert(BinaryNode actual, T e, K k){

        if(k.compareTo(actual.key) < 0){ // Verificamos en la izquierda
			if (actual.left == null) 
                actual.left = new BinaryNode(k, e, actual);
			else
                insert(actual.left, e, k);
		} else { // Verificar en la derecha
            if (actual.rigth == null)
			actual.rigth = new BinaryNode(k, e, actual);
					else
				insert(actual.rigth, e, k);
				
		}
	}
		
	/**
	* Elimina el nodo con clave k del árbol.
	* @param k la clave que pertene al nodo a eliminar.
	* @return el elemento almacenado en el nodo a eliminar,
	* null si el nodo con clave k no existe.
	*/
    @Override
    public T delete(K k) {
		/** Caso donde el árbol esté vacío. */
		if (isEmpty()) 
			return null;

		BinaryNode actual = retrieve(root, k);
		T aux = actual.element;

		/** Caso donde solo hay un elemento. */
		if (actual == root) {
			root = null;
			return aux;
		}

		/** Caso donde no tiene hijos. */
	    if(actual.left == null && actual.rigth == null) {
			if (actual.parent.left == actual) 
				actual.parent.left = null;
			else
				actual.parent.rigth = null;
	
			return aux;
		}

		/** Caso donde tiene dos hijos */
		if (actual.left != null && actual.rigth != null) {
			BinaryNode maxNode = actual;

			/** Buscamos el máximo de los mínimos */
			do {
				maxNode = maxNode.left;
			} while (maxNode.left != null && maxNode.rigth == null);

			while (maxNode.rigth != null) 
				maxNode = maxNode.rigth;

			/** Actualizamos las referencias */
			swap(actual, maxNode);

			if (maxNode.left != null){
				swap(maxNode, maxNode.left);
				maxNode.left = null;
			} else 
				maxNode.parent.rigth = null;

			return aux;			

		
		/** Caso donde tiene solo un hijo */
		} else {

			if (actual.left != null){
				swap(actual, actual.left);
				actual.left = null;
			}else {
				swap(actual, actual.rigth);
				actual.rigth = null;
			}

			return aux;
		}
    }

	/**
	 * Cambia de posición dos elementos entre sí.
	 * @param a el índice del primer elemento a cambiar.
	 * @param b el índice del segundo elemento a cambiar.
	 */
	private void swap(BinaryNode a, BinaryNode b){
		a.element = b.element;
		a.key = b.key;
	}


	/**
	* Encuentra la clave k con valor o peso mínimo del árbol.
	* @return el elemento con llave de peso mínimo.
	*/
    @Override
    public T findMin() {
		if (isEmpty()) 
			return null;
	
		BinaryNode actual = root;
		
		while (actual.left != null) 
			actual = actual.left;

		return actual.element;
	
    }
			
	/**
	* Encuentra la clave k con valor o peso máximo del árbol.
	* @return el elemento con llave de peso máximo.
	*/
    @Override
    public T findMax() {
        if (isEmpty())
			return null;
			
		BinaryNode actual = root;

        while (actual.rigth != null) 
			actual = actual.rigth;

		return actual.element;
    }

	/** 
	* Recorre el árbol en forma de Preorden.
	*/
    @Override
    public void preorden() {
        preorden(root);
    }

    public void preorden(BinaryNode actual){

		if (actual == null)
			return;

        System.out.println(actual.element);

        if (actual.left != null)
            preorden(actual.left);

		if (actual.rigth != null)
            preorden(actual.rigth);
    }

	/** 
	* Recorre el árbol en forma de Inorden. 
	*/
    @Override
    public void inorden() {
        inorden(root);
    }

	public void inorden(BinaryNode actual){

		if (actual == null)
			return;

        if (actual.left != null)
            inorden(actual.left);

		System.out.println(actual.element);

		if (actual.rigth != null)
            inorden(actual.rigth);
    }

	/** 
	* Recorre el árbol en forma de Postorden.
	*/
    @Override
    public void postorden() {
        postorden(root);
    }

	public void postorden(BinaryNode actual){

		if (actual == null)
			return;

        if (actual.left != null)
            postorden(actual.left);

		if (actual.rigth != null)
            postorden(actual.rigth);

		System.out.println(actual.element);
    }

	/**
	* Verifica si el árbol es vacío.
	* @return true si el árbol es vacío, false en otro caso.
	*/
    @Override
    public boolean isEmpty() {
        if (root == null)
            return true;
        return false;
    }

	public static void main(String[] args) {
		TDABinarySearchTree<Integer, String> tree = new BinarySearchTree<>();
		Scanner sc = new Scanner(System.in);
		String respuesta= " ", respuesta1 = " ";

				System.out.println("\n      BINARY SEARCH TREE ");
		do {
			try {
				System.out.println("\n----------------------------\n" +
								"           Menú        \n" +
                                " 1)  Obtener elemento \n" +
                                " 2)  Insertar elemento \n" +
                                " 3)  Eliminar elemento \n" +
                                " 4)  Find Min \n" +
                                " 5)  Find Max \n" +
                                " 6)  Comprobar si es vacío\n" +
                                " 7)  Recorrer el árbol \n" +
                                " 8)  Salir");
                respuesta = sc.nextLine();

				switch (respuesta) {
					case "1": // retrieve -- obtener elemento
						
						int k; // Clave.
						String ele; // Elemento.

						if (tree.isEmpty()) {
							System.out.println("\nEl árbol está vacío.");
						}else {
							System.out.println("Clave del elemento: ");
							k = Integer.parseInt(sc.nextLine());
							System.out.println("El elemento con clave " + k + " es: " + tree.retrieve(k));
						}
						break;
				
					case "2": // Instertar nodo

						do {
							try {
								
								System.out.println("\nEscribe el elemento que deseas agregar : ");
								ele = sc.nextLine();
								System.out.println("Escribe la clave del elemento (número): ");
								k = Integer.parseInt(sc.nextLine());
								tree.insert(ele, k);
								System.out.println("\nSe agregó correctamente.");
								break;
							
							} catch (Exception e) {
								System.out.println("\nOpción inválida.");
							}
						} while (true);
						break;
					
					case "3": // Elimina Nodo

						if (tree.isEmpty()) {
							System.out.println("\nEl árbol está vacío.");
							break;
						} else {							
							System.out.println("\nColoca la clave del elemento que deseas eliminar: ");
							k = Integer.parseInt(sc.nextLine());
							System.out.println("\nEliminaste el elemento "+ k + " con la clave "+ tree.delete(k));

						}break;

					case "4": // Encontrar el elemento mínimo
					   
						if (tree.isEmpty()) {
							System.out.println("\nEl árbol está vacío.");
						}else {
							System.out.println("\nEl elemento con clave mínima en el árbol es: "+ tree.findMin());
						}
						break;

					case "5": // Encontrar el elemento máximo
						
						if (tree.isEmpty()) {
							System.out.println("\nEl árbol está vacío.");
						}else {
							System.out.println("\nEl elemento con clave máxima en el árbol es: "+ tree.findMax());
						}
						break;

					case "6": // Comprobar si es vacío
						
						if(tree.isEmpty()){
							System.out.println("\nEl árbol está vacío.");
							break;						
						}else{
							System.out.println("\nEl árbol no está vacío.");
							break;
						}

					case "7":
					    	try {
								System.out.println("\n        RECORRIDOS      ");
								System.out.println(" a) Preorden     ");
								System.out.println(" b) Inorden     ");
								System.out.println(" c) Postorden     ");

								respuesta1 = sc.nextLine();

								switch (respuesta1) {
									case "a":

										if (tree.isEmpty()) {
											System.out.println("El árbol está vacío.");
										} else {
											System.out.println("\nPreorden del árbol");
											tree.preorden();
											
										}break;
										

									case "b":	

										if (tree.isEmpty()) {
											System.out.println("El árbol está vacío.");
										} else {
											System.out.println("\nInorden del árbol");
											tree.inorden();
											
										}break;
								
									case "c":
										
										if (tree.isEmpty()) {
											System.out.println("El árbol está vacío.");
										} else {
											System.out.println("\nPostorden del árbol");
											tree.postorden();
											
										}break;

									default:
									    System.out.println("\nError: Opción inválida.\nIngresa una de las opciones.");
										break;
								}

							} catch (Exception e) {
								System.out.println("\nError: Opción inválida.");
								sc.nextLine();
							}
							break;
						
					case "8":
						System.out.println("\nHasta la próximaa! :)");
						return;

					default:
						System.out.println("\nError: Opción inválida.\nIngresa una de las opciones.");
						break;
				}
			} catch (Exception e) {
                System.out.println("\nError: Vuelvelo a intentar\n");
			}	
		} 
		while (respuesta != "8");
	}	
}

