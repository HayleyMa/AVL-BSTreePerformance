/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeTime;

public class AVLTreeException extends Exception 
{

    /**
     * Creates a new instance of <code>AVLTreeException</code> without detail
     * message.
     */
    public AVLTreeException() { }

    /**
     * Constructs an instance of <code>AVLTreeException</code> with the
     * specified detail message.
     * @param msg the detail message.
     */
    public AVLTreeException(String msg) 
    {
        super(msg);
    }
}
