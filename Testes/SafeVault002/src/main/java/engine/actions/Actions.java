package engine.actions;

import engine.actions.cipher.Cipher;
import engine.actions.copy.Copy;
import engine.actions.create.Create;
import engine.actions.decipher.Decipher;
import engine.actions.delete.Delete;
import engine.actions.list.List;
import engine.actions.move.Move;

public class Actions {
	
	private Create create;
	private List list;
	private Copy copy;
	private Move move;
	private Delete delete;
	
	private Cipher cipher;
	private Decipher decipher;

	public Actions() {
		create = new Create();
		list = new List();
		copy = new Copy(this);
		move = new Move(this);
		delete = new Delete(this);
		
		cipher = new Cipher(this);
		decipher = new Decipher(this);
	}
	
	public Create create() {
		return create;
	}
	
	public List list() {
		return list;
	}
	
	public Copy copy() {
		return copy;
	}
	
	public Move move() {
		return move;
	}
	
	public Delete delete() {
		return delete;
	}
	
	public Cipher cipher() {
		return cipher;
	}
	
	public Decipher decipher() {
		return decipher;
	}
}
