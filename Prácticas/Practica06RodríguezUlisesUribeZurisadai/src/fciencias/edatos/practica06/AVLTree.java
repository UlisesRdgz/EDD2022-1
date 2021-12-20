package fciencias.edatos.practica06;

import java.util.Scanner;

/**
* Define las operaciones sobre un árbol AVL.
* @author Ulises Rodríguez García
* @author Zurisadai Uribe García
* @version 20 Diciembre 2021.
* @since Estructuras de Datos 2022-1. Práctica 06.
*/
public class AVLTree<K extends Comparable<K>, T> implements TDABinarySearchTree<K, T> {

    /**
	 * Nodo de un arbol AVL.
	 */
	public class AVLNode {

		/** Altura del nodo. */
		public int altura;

		/** Hijo izquierdo. */
		public AVLNode left;

		/** Hijo derecho. */
		public AVLNode rigth;

		/** Padre del nodo. */
		public AVLNode parent;

		/** Elemento almacenado en el nodo. */
		public T element;

		/** Clave del nodo. */
		public K key;

		/**
		 * Crea un nuevo nodo AVL
		 * @param element el elemento a almacenar.
		 * @param key la clave del nodo.
		 * @param padre el padre del nodo
		 */
		public AVLNode(K key, T element, AVLNode parent) {
			this.element = element;
			this.key = key;
			this.parent = parent;
			this.altura = this.getAltura();
		}

		/**
		 * Calcula la altura del nodo.
		 */
		public int getAltura() {
			/** Si es una hoja */
			if(left == null && rigth==null)
				return 0;

			/** Dos hijos */
			else if(left != null && rigth != null){ // Dos hijos
				int max = left.getAltura() > rigth.getAltura() ? left.getAltura() : rigth.getAltura();
				return 1 + max;

			/** Si tiene solo un hijo */
			} else{
				boolean hasLeft = left!=null;
				return 1 + (hasLeft ? left.getAltura() : rigth.getAltura());
			}
		}

		/**
		 * Actualiza la altura del nodo.
		 */
		public void actualizaAltura() {
			this.altura = this.getAltura();
		}
	}

    /** Raíz */
    private AVLNode root;

	/**
	* Recupera el objeto con clave k.
	* @param k la clave a buscar.
	* @return el elemento con clave k o null si no existe.
	*/
    @Override
    public T retrieve(K k) {
        AVLNode node = retrieve(k, root);
		if (node == null)
			return null;
		return node.element;
    }

    /**
	 * Obtenia el nodo con una clave específica.
	 * @param k la clave a buscar
	 * @param actual el nodo actual
	 * @return el nodo con clave k o null si no existe.
	 */
	private AVLNode retrieve(K k, AVLNode actual) {
		// No se encuentra el elemento.
		if (actual == null)
			return null;

		int compare = k.compareTo(actual.key);

		// Si encontramos el elemento
		if (compare == 0)
			return actual;

		// Comparamos los elementos
		if (k.compareTo(actual.key) < 0) // Verificamos en la izquierda
			return retrieve(k, actual.left);
		else // Verificar en la derecha
			return retrieve(k, actual.rigth);
	}

	/**
	* Inserta un nuevo elemento al árbol.
	* @param e el elemento a ingresar.
	* @param k la clave del elemento a ingresar.
	*/
    @Override
    public void insert(T e, K k) {
        if (isEmpty()) {
        	root = new AVLNode(k, e, null);
			return;
		}

        AVLNode v = insert(k, e, root);

		/** Rebalancea */
		balance(v);
    }

	/**
	 * Inserta un nodo de forma recursiva.
	 * @param e el elemento a insertar
	 * @param k es la clave del nodo a insertar
	 * @param actual el nodo actual
	 * @return 
	 */
	public AVLNode insert(K k, T e, AVLNode actual) {

		/** Verificamos en la izquierda */
		if(k.compareTo(actual.key) < 0) {
			if (actual.left == null){
        		actual.left = new AVLNode(k, e, actual);
				return actual.left;

			} else
				return insert(k, e, actual.left);

		/** Verificar en la derecha */
		} else {
            if (actual.rigth == null){
				actual.rigth = new AVLNode(k, e, actual);
				return actual.rigth;

			} else
				return insert(k, e, actual.rigth);	
		}
	}

	/**
	* Elimina el nodo con clave k del árbol.
	* @param k la clave perteneciente al nodo a eliminar.
	* @return el elemento almacenado en el nodo a eliminar.
	* null si el nodo con clave k no existe.
	*/
    @Override
    public T delete(K k) {
        AVLNode v = retrieve(k, root);

		/** El elemento que queremos eliminar no está en el árbol*/
		if (v == null)
			return null;

		T eliminado = v.element;

		/** Eliminar con auxiliar */
		AVLNode w = delete(v);

		/** Rebalancea */
		balance(w);
		return eliminado;
    }

	/**
	 * Método auxiliar para borrar un nodo.
	 * @param actual nodo sobre el cual se hará recursión.
	 * @return regresa el padre del nodo.
	 */
	private AVLNode delete(AVLNode actual) {

		/** Caso donde solo hay un nodo */
		if (actual == root && (actual.left == null && actual.rigth == null)){
			root = null;
			return null;
		}

		/** Caso donde tiene dos hijos. */
		if (actual.left != null && actual.rigth != null) {
			AVLNode mayor = findMax(actual.left);
			swap(mayor, actual);
			return delete(mayor);

		/** Caso donde no tiene hijos. */
		} else if (actual.left == null && actual.rigth == null) {
			if (actual.parent.left == actual) 
				actual.parent.left = null;
			else
				actual.parent.rigth = null;

			return actual.parent;

		/** Caso donde tiene solo un hijo. */
		} else {
			if (actual.left != null) {
				swap(actual, actual.left);
				return delete(actual.left);
				
			} else {
				swap(actual, actual.rigth);
				return delete(actual.rigth);
			}
		}
	}

	/**
	 * Método auxiliar que intercambia la posición del 
	 * primer nodo con el segundo.
	 * @param v primer nodo a cambiar.
	 * @param w segundo nodo a cambiar.
	 */
	private void swap(AVLNode v, AVLNode w) {
		T value = v.element;
		K llave = v.key;
		v.element = w.element;
		v.key = w.key;
		w.element = value;
		w.key = llave;
	}

	/**
	* Encuentra la clave k con valor o peso mínimo del árbol.
	* @return el elemento con llave de peso mínimo.
	*/
    @Override
    public T findMin() {
        if (isEmpty()) 
			return null;
	
		AVLNode actual = root;
		
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
			
		AVLNode actual = root;

        while (actual.rigth != null) 
			actual = actual.rigth;

		return actual.element;
    }

	/**
	 * Encuentra la clave k con valor o peso máximo del árbol
	 * empezando desde el nodo dado.
	 * @return el nodo con llave de peso máximo.
	 */
	private AVLNode findMax(AVLNode actual) {
		if (isEmpty())
			return null;

        while (actual.rigth != null) 
			actual = actual.rigth;

		return actual;
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

	/**
	 * Método para balancear el árbol.
	 * @param actual el nodo desde donde se comienza
	 * a balancear el árbol
	 */
	private void balance(AVLNode actual) {

		if (actual == null)
			return;
			
		/** Desbalance a la derecha */
		if (getHeigh(actual.rigth) == getHeigh(actual.left) + 2) {
				
			/** Caso 1 */
			if (getHeigh(actual.rigth.rigth) == getHeigh(actual.rigth) -1) {
				leftRotation(actual);
		
			/** Caso 2 */
			} else {
				rightRotation(actual.rigth);
				leftRotation(actual);
			}
				
		}

		/** Desbalance a la izquierda */
		if (getHeigh(actual.left) == getHeigh(actual.rigth) + 2) {
			/** Caso 1 */
			if (getHeigh(actual.left.left) == getHeigh(actual.left) -1) {
				rightRotation(actual);
		
			/** Caso 2 */
			} else {
				leftRotation(actual.left);
				rightRotation(actual);
			}
		}

		balance(actual.parent);
	}

	/**
	 * Método para obtener la altura de cada nodo.
	 * @param actual nodo para obtener la altura.
	 * @return la altura del nodo.
	 */
	private int getHeigh(AVLNode actual){

		if (actual == null) 
			return -1;
		
		return actual.getAltura();
	}

	/**
	 * Rota el árbol a la derecha.
	 * @param actual nodo a partir el cual
	 * será rotado.
	 */
	private void rightRotation(AVLNode actual) {
		AVLNode newRoot = actual.left;
		AVLNode aux = newRoot.rigth;

		swap(newRoot, actual);

		actual.left = actual.left.left;
		if (actual.left != null)
			actual.left.parent = actual;

		newRoot.rigth = actual.rigth;
		if (actual.rigth != null) 
			actual.rigth.parent = newRoot;
		
		actual.rigth = newRoot;
		newRoot.parent = actual;

		newRoot.left = aux;
		if (aux != null)
			aux.parent = newRoot;
	}

	/**
	 * Rota el árbol a la izquierda.
	 * @param actual nodo a partir el cual
	 * será rotado.
	 */
	private void leftRotation(AVLNode actual) {
		AVLNode newRoot = actual.rigth;
		AVLNode aux = newRoot.left;

		swap(newRoot, actual);

		actual.rigth = actual.rigth.rigth;
		if (actual.rigth != null)
			actual.rigth.parent = actual;

		newRoot.left = actual.left;
		if (actual.left != null) 
			actual.left.parent = newRoot;
		
		actual.left = newRoot;
		newRoot.parent = actual;

		newRoot.rigth = aux;
		if (aux != null)
			aux.parent = newRoot;
	}

	/**
	 * Recorre el árbol en preorden.
	 */
    @Override
    public void preorden() {
		preorden(root);
    }

	/**
	 * Método auxiliar de preorden.
	 * @param actual nodo que se irá recorriendo.
	 */
    public void preorden(AVLNode actual) {

		if (actual == null)
			return;

        System.out.println("Elemento: " + actual.element +
						   "\tClave: " + actual.key);

        preorden(actual.left);
        preorden(actual.rigth);
    }

	/**
	 * Recorre el árbol en inorden.
	 */
    @Override
    public void inorden() {
		inorden(root);
    }

	/**
	 * Método auxiliar de inorden.
	 * @param actual nodo que se irá recorriendo.
	 */
	public void inorden(AVLNode actual) {

		if (actual == null)
			return;

        inorden(actual.left);

		System.out.println("Elemento: " + actual.element +
						   "\tClave: " + actual.key);

        inorden(actual.rigth);
    }

	/**
	 * Recorre el árbol en postorden.
	 */
    @Override
    public void postorden() {
        postorden(root);
    }

	/**
	 * Método auxiliar de postorden.
	 * @param actual nodo que se irá recorriendo.
	 */
	public void postorden(AVLNode actual){

		if (actual == null)
			return;

        postorden(actual.left);
		postorden(actual.rigth);

		System.out.println("Elemento: " + actual.element +
						   "\tClave: " + actual.key);
    }

	public static void main(String[] args) {

		AVLTree<Integer, String> arbol = new AVLTree<>();
		Scanner sc = new Scanner(System.in);
		String respuesta = "", respuesta1 = "";

			System.out.println("    AVLTree    ");
		do {
			try {		
				System.out.println("\n-------------------------");
				System.out.println("           Menu          ");
				System.out.println( " 1)  Obtener elemento \n" +
									" 2)  Insertar elemento \n" +
									" 3)  Eliminar elemento \n" +
									" 4)  Find Min \n" +
									" 5)  Find Max \n" +
									" 6)  Comprobar si es vacío\n" +
									" 7)  Recorrer el árbol\n"+
									" 8)  Salir");
				
				respuesta = sc.nextLine();	
				
				switch (respuesta) {
					case "1": // Retrieve -- regresar algún elemento.
						
						int clave; // Clave.
						String elemento; // Elemento.

						if (arbol.isEmpty()) {
							System.out.println("\nEl árbol está vacío.");
						}else {

							do {
								try {
									System.out.println("\nClave del elemento: ");
									clave = Integer.parseInt(sc.nextLine());
									String ele = arbol.retrieve(clave);
								
									if (ele == null) 
										System.out.println("\nNo se existe el elemento con clave: " + clave);
									else 
										System.out.println("\nEl elemento con clave " + clave + " es: " + ele);
									break;

								} catch (Exception e) {
									System.out.println("\nOpción inválida.");
								}
							} while (true);

							break;
						}
						break;

					case "2": // Insert -- Inserta algún elemento.
						do {
							try {
								
								System.out.println("\nEscribe el elemento que deseas agregar : ");
								elemento = sc.nextLine();
								System.out.println("Escribe la clave del elemento (número): ");
								clave = Integer.parseInt(sc.nextLine());
								arbol.insert(elemento, clave);
								System.out.println("\nSe agregó correctamente.");
								break;
							
							} catch (Exception e) {
								System.out.println("\nOpción inválida.");
							}
						} while (true);
						break;							

					case "3": // Delete -- borra algún elemento.
					
						if (arbol.isEmpty()) {
							System.out.println("\nEl árbol está vacío.");
							break;
						}

						do {
							try {

								System.out.println("\nColoca la clave del elemento que deseas eliminar: ");
								clave = Integer.parseInt(sc.nextLine());
								String ele = arbol.delete(clave);

								if (ele == null) 
									System.out.println("\nNo se existe el elemento con clave: " + clave);
								else 
									System.out.println("\nEliminaste el elemento "+ ele + " con la clave "+ clave);
								break;

							} catch (Exception e) {
								System.out.println("\nOpción inválida.");
							}
						} while (true);

						break;

					case "4": // findMin -- Encuentra el elemento mínimo.

						if (arbol.isEmpty()) {
							System.out.println("\nEl árbol está vacío.");
						}else {
							System.out.println("\nEl elemento con clave mínima en el árbol es: "+ arbol.findMin());
						}
						break;

					case "5": // findMax -- Encuentra el elemento máximo.

						if (arbol.isEmpty()) {
							System.out.println("\nEl árbol está vacío.");
						}else {
							System.out.println("\nEl elemento con clave máxima en el árbol es: "+ arbol.findMax());
						}
						break;


					case "6": // isEmpty -- Verifica si el árbol está vacío.
					
						if(arbol.isEmpty()){
							System.out.println("\nEl árbol está vacío.");
							break;						
						}else{
							System.out.println("\nEl árbol no está vacío.");
							break;
						}


					case "7":

						boolean flag = true;

						if (arbol.isEmpty()) {
							System.out.println("\nEl árbol está vacío.");
						} else {

							do {
								try {
									System.out.println("\n-------------------------");
									System.out.println("        RECORRIDOS       ");
									System.out.println(" a) Preorden     ");
									System.out.println(" b) Inorden     ");
									System.out.println(" c) Postorden     ");
									System.out.println(" d) Regresar al menú     ");
		
									respuesta1 = sc.nextLine();
		
									switch (respuesta1) {

										case "a": 
											System.out.println("\nPreorden del árbol\n");
											arbol.preorden();
											break;
		
										case "b":	
											System.out.println("\nInorden del árbol");
											arbol.inorden();
											break;
									
										case "c":
											System.out.println("\nPostorden del árbol");
											arbol.postorden();
											break;

										case "d":
											flag = false;
											break;
		
										default:
											System.out.println("\nError: Opción inválida.\nIngresa una de las opciones.");
											break;
									}
		
								} catch (Exception e) {
									System.out.println("\nError: Opción inválida.");
									sc.nextLine();
								}
							} while (true && flag);
						}
						break;
						
					case "8":
						return;
				
					default:
						System.out.println("\nError, Opción inválida.\nIngresa una de las opciones.");
						break;
				}
								
			} catch (Exception e) {
				System.out.println("Error. Vuelvelo a intentar.");
			}
		} while (respuesta != "9");
	}
}
