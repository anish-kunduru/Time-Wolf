package GameServer.GameEngine;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GameEngineRemote extends Remote {

	/**
	 * Get the name of the game.
	 * @return the name of the game
	 */
	public String getName() throws RemoteException;
	
	
	/**
	 * Is the game full?
	 * @return true if full
	 */
	public abstract boolean isFull() throws RemoteException;

	/**
	 * Is the game running?
	 * @return true if game is running
	 */
	public abstract boolean isRunning() throws RemoteException;

	/**
	 * Is the game finished?
	 * @return true if the game is finished
	 */
	public abstract boolean isFinished() throws RemoteException;

	/**
	 * Has the game started?
	 * @return true if the game started
	 */
	public abstract boolean hasStarted() throws RemoteException;

}