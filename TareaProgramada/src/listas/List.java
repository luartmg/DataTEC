package listas;

public class List < T >
{
	private ListNode< T > _Head;
	private ListNode< T > _Tail;
	private int _Size;
	
	public List( )
	{
		this._Head = this._Tail = null;
	}
	
	public int size( )
	{
		return this._Size;
	}
	
	public boolean isEmpty( )
	{
		return _Head == null;
	}
	
	public void add( T pDato )
	{
		this.addManager(this.size(), pDato);
	}
	
	public void addToStart( T pDato )
	{
		this.addManager(0, pDato);
	}
	
	public void add( T pDato, int pIndex )
	{
		this.addManager(pIndex, pDato);
	}
	private void addManager(int pIndice, T pDato)
	{
		if(pIndice <= this._Size)
		{
			ListNode<T> tmp = new ListNode<T>(pDato);
			//Anadir un primer elemento
			if(this._Size == 0)
			{
				this._Head = tmp;
				this._Tail = tmp;
			}
			//Anadir al inicio
			else if(pIndice == 0)
			{	
				tmp.setNext(this._Head);
				this._Head.setPrev(tmp);
				this._Head = tmp;
			}
			//Anadir al final
			else if(pIndice == this._Size)
			{
				this._Tail.setNext(tmp);
				tmp.setPrev(this._Tail);
				this._Tail = tmp;
			}
			//Anadir en un determinado indice
			else
			{
				ListNode<T> pivote = this._Head;
				for(int x = 0; x < pIndice - 1; x++)
				{
					pivote = pivote.getNext();
				}
				tmp.setPrev(pivote);
				tmp.setNext(pivote.getNext());
				pivote.getNext().setPrev(tmp);
				pivote.setNext(tmp);
			}
			this._Size++;
		}
		else
			System.out.print("Indice Fuera de Rango");
	}
	
	public void print()
	{
		ListNode<T> pivote = this._Head;
		System.out.print("[ ");
		while(pivote != null)
		{
			System.out.print(pivote.getData() + ", ");
			pivote = pivote.getNext();
		}
		System.out.println(" ]");
	}
	
	/**
	 * 
	 * @param pIndice
	 * @return
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public T search(int pIndice) throws ArrayIndexOutOfBoundsException
	{
		if(pIndice < this._Size)
		{
			ListNode<T> tmp = this._Head;
			for(int x =0 ; x < this._Size; x++)
			{
				tmp = tmp.getNext();
			}
			return tmp.getData();
		}
		else
			throw new ArrayIndexOutOfBoundsException();
	}
	
	/**
	 * Remover por medio de un indice un elemento de la lista.
	 * @param pIndex {@link Integer}
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public void remove(int pIndex) throws ArrayIndexOutOfBoundsException
	{
		if(pIndex < this._Size)
		{
			//Eliminar el ultimo elemento de la lista
			if(this._Size == 1)
			{
				this._Head = this._Tail = null;
			}
			//Eliminar el primer elemento de la lista
			else if(pIndex == 0)
			{
				this._Head.getNext().setPrev(null);
				this._Head = this._Head.getNext();
			}
			//Eliminar el ultimo elemento de la lista
			else if(pIndex == this._Size - 1)
			{
				this._Tail.getPrev().setNext(null);
				this._Tail = this._Tail.getPrev();
			}
			//Eliminar un elemento en otra posicion
			else
			{
				ListNode<T> tmp = this._Head;
				for(int x = 0; x != pIndex; x++)
				{
					tmp = tmp.getNext();
				}
				tmp.getPrev().setNext(tmp.getNext());
				tmp.getNext().setPrev(tmp.getPrev());
			}
			this._Size--;
		}
		else
			throw new ArrayIndexOutOfBoundsException("Indice: " + pIndex );
	}
}