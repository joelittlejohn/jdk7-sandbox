/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.joelittlejohn.jdk7sandbox.forkjoin;

import com.github.joelittlejohn.jdk7sandbox.forkjoin.pgm.PgmImage;

/**
 *
 * @author joe
 */
public interface Filter {

    PgmImage apply(PgmImage original);

}
