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
import java.util.ArrayList;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.google.inject.Guice;
import com.google.inject.Injector;

import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.pdsw.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.pdsw.samples.entities.Cliente;
import edu.eci.pdsw.samples.entities.Item;
import edu.eci.pdsw.samples.entities.ItemRentado;
import edu.eci.pdsw.samples.entities.TipoItem;
import edu.eci.pdsw.samples.services.ExcepcionServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquiler;
import edu.eci.pdsw.samples.services.ServiciosAlquilerFactory;

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
     *
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
        System.out.println(cm.consultarCliente(1023437828)); //Se consulta sólo un cliente.
        //Agregamos un nuevo Item Rentado para un cliente dado.
        //cm.agregarItemRentadoACliente(0, 9, new Date(18,10,2),new Date(18,10,20));
        System.out.println();
        System.out.println("------------ Item Rentado ------------");
        System.out.println(cm.consultarItemRentado(9, 2133541)); //Consultamos el item rentado agregado.
        System.out.println();

        //Crear el mapper y usarlo.
        ItemMapper im = sqlss.getMapper(ItemMapper.class);
        //Insertamos un nuevo Item.
        //im.insertarItem(new Item(new TipoItem(2,"Juego"), 9, "Zelda Breath of the Wild", "Juego nintendo Switch", new Date(17,3,3), 25000, "Digital", "SandBox"));
        System.out.println("------------ Items ------------");
        System.out.println(im.consultarItems()); //Se consultan todos los items.
        System.out.println();
        System.out.println("------------ Item Específico ------------");
        System.out.println(im.consultarItem(9)); //Se consulta el item ingresado.

        ServiciosAlquilerFactory servicioAlquilerFactory = ServiciosAlquilerFactory.getInstance();
        ServiciosAlquiler servicioAlquiler = servicioAlquilerFactory.getServiciosAlquiler();
        try {
            //Consutar Cliente
            System.out.println(servicioAlquiler.consultarCliente(2133541));
            //Consultar Items Clientes
            System.out.println(servicioAlquiler.consultarItemsCliente(2133541));
            //consultar clientes
            System.out.println(servicioAlquiler.consultarClientes());
            //consultar Items
            System.out.println(servicioAlquiler.consultarItem(9));
            //consultar items disponibles
            System.out.println(servicioAlquiler.consultarItemsDisponibles());
            //consultar multa alquiler
            System.out.println(servicioAlquiler.consultarMultaAlquiler(8, new Date(2019, 3, 3)));
            //consultar tipo item
            System.out.println(servicioAlquiler.consultarTipoItem(9));
            //consultar tipos item
            System.out.println(servicioAlquiler.consultarTiposItem());
            //registrar alquiler cliente
            //servicioAlquiler.registrarAlquilerCliente(new Date(118,10,7),2133541,servicioAlquiler.consultarItem(9), 10);
            //Registrar cliente
            //servicioAlquiler.registrarCliente(new Cliente("Carlos Medina",1023437828,"5381254","crr 54 N 58z-16777 SUR","carlos.medina@mail.escuelaingedu.co"));
            //Consultar costo alquiler
            System.out.println(servicioAlquiler.consultarCostoAlquiler(9, 1));
            //Actualizar tarifa item
            //servicioAlquiler.actualizarTarifaItem(9, 30000);
            //Registrar item
            //servicioAlquiler.registrarItem(new Item(new TipoItem(2, "Juego"), 12, "Hollow Knight", "Juego INDIE de plataformas", new Date(117,2,12), 15000, "Digital", "Plataformas"));
            //Vetar cliente
            //servicioAlquiler.vetarCliente(1023437828, true);
        } catch (ExcepcionServiciosAlquiler e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        sqlss.commit();
        sqlss.close();
    }
}
