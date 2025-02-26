/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository;

import model.RealEstate;

/**
 *
 * @author DELL
 */
public interface IRealEstateRepository extends Repository<RealEstate>{
    RealEstate findEstateById(String id);
    void deleteRE(RealEstate r);
}
