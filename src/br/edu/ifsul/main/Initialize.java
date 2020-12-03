/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.main;

import br.edu.ifsul.files.Conexao;
import java.io.IOException;

/**
 *
 * @author darlan
 */
public class Initialize {
    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("sudo ./createStructure /home/darlan");
        Runtime.getRuntime().exec("/home/darlan/.openmpi/bin/mpirun --allow-run-as-root -np 1 java -jar -Xms512m -Xmx6144m  ~/ABISM/ABISM.jar /home/darlan/ABISM/experiments/");
        
    }
}
//Conexao file = new Conexao();
//file.createWD();