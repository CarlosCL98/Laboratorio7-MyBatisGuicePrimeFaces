/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.pdsw.samples.services.client;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException {
        
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();

        //Crear el mapper y usarlo.
        ClienteMapper cm = sqlss.getMapper(ClienteMapper.class);
        System.out.println("------------ Clientes ------------");
        System.out.println(cm.consultarClientes()); //Se consultan todos los clientes.
        System.out.println();
        System.out.println("------------ Cliente Específico ------------");
        System.out.println(cm.consultarCliente(0)); //Se consulta sólo un cliente.
        //Agregamos un nuevo Item Rentado para un cliente dado.
        /*cm.agregarItemRentadoACliente(0, 9, new Date(18,10,2),new Date(18,10,20));*/
        System.out.println();
        System.out.println("------------ Item Rentado ------------");
        System.out.println(cm.consultarItemRentado(9,0)); //Consultamos el item rentado agregado.
        System.out.println();
        
        //Crear el mapper y usarlo.
        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        //Insertamos un nuevo Item.
        /*im.insertarItem(new Item(new TipoItem(2,"Juego"), 9, "Zelda Breath of the Wild", "Juego nintendo Switch", new Date(17,3,3), 25000, "Digital", "SandBox"));*/
        System.out.println("------------ Items ------------");
        System.out.println(im.consultarItems()); //Se consultan todos los items.
        System.out.println();
        System.out.println("------------ Item Específico ------------");
        System.out.println(im.consultarItem(9)); //Se consulta el item ingresado.
        
        sqlss.commit();
        sqlss.close();
    }
}