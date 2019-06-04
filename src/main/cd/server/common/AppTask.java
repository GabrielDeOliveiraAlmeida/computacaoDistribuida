/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main.cd.server.common;

/**
 *
 * @author gabriel
 * @param <T>
 */

public interface AppTask<Object> {
    Object calculate();
}


