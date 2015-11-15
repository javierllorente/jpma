/*
 * Copyright (C) 2012-2015 Javier Llorente <javier@opensuse.org>
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
package com.javierllorente.jpma;

/**
 * Clase Provincia (enum)
 * 
 * @author javier
 */
public enum Provincia {

    ALAVA(1),
    ALBACETE(2),
    ALICANTE(3),
    ALMERIA(4),
    AVILA(5),
    BADAJOZ(6),
    ISLAS_BALEARES(7),
    BARCELONA(8),
    BURGOS(9),
    CACERES(10),
    CADIZ(11),
    CASTELLON(12),
    CIUDAD_REAL(13),
    CORDOBA(14),
    LA_CORUNA(15),
    CUENCA(16),
    GERONA(17),
    GRANADA(18),
    GUADALAJARA(19),
    GUIPUZCOA(20),
    HUELVA(21),
    HUESCA(22),
    JAEN(23),
    LEON(24),
    LERIDA(25),
    LOGRONO(26),
    LUGO(27),
    MADRID(28),
    MALAGA(29),
    MURCIA(30),
    NAVARRA(31),
    ORENSE(32),
    OVIEDO(33),
    PALENCIA(34),
    LAS_PALMAS(35),
    PONTEVEDRA(36),
    SALAMANCA(37),
    TENERIFE(38),
    SANTANDER(39),
    SEGOVIA(40),
    SEVILLA(41),
    SORIA(42),
    TARRAGONA(43),
    TERUEL(44),
    TOLEDO(45),
    VALENCIA(46),
    VALLADOLID(47),
    VIZCAYA(48),
    ZAMORA(49),
    ZARAGOZA(50),
    CEUTA(51),
    MELILLA(52);

    private final int id;

    private Provincia(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
