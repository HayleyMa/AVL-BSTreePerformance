/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeTime;

public class BSTreeException extends Exception 
{
    /**
     * Creates a new instance of <code>BSTreeException</code> without detail
     * message.
     */
    public BSTreeException() { }

    /**
     * Constructs an instance of <code>BSTreeException</code> with the specified
     * detail message.
     * @param msg the detail message.
     */
    public BSTreeException(String msg) 
    {
        super(msg);
    }
}
