package hu.codingmentor.mobile.webshop.service;

import hu.codingmentor.mobile.webshop.dto.MobileDTO;
import hu.codingmentor.mobile.webshop.exception.ItemDontExistsException;
import hu.codingmentor.mobile.webshop.exception.ItemSoldOutException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Singleton
@Startup
@LocalBean
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
public class InventoryService {

    private List<MobileDTO> inventory = new ArrayList();

    public InventoryService() {
        // Default constructor
    }

    @PostConstruct
    private void init() {
        inventory.add(new MobileDTO("iPhone 6", "Apple", 200, 3));
        inventory.add(new MobileDTO("Samsung S6", "Samsung", 150, 12));
        inventory.add(new MobileDTO("Xperia L", "Sony", 68, 5));
        inventory.add(new MobileDTO("Skate", "ZTE", 40, 0));
    }

    @Lock(LockType.WRITE)
    public MobileDTO addMobile(MobileDTO mobile) {
        for (MobileDTO m : inventory) {
            if (m.getManufacturer().equals(mobile.getManufacturer())
                    && m.getPrice().equals(mobile.getPrice())
                    && m.getType().equals(mobile.getType())) {
                m.setPiece(m.getPiece() + mobile.getPiece());
                return m;
            }
        }
        inventory.add(mobile);
        return mobile;
    }

    @Lock(LockType.READ)
    public List<MobileDTO> getMobileList() {
        return inventory;
    }

    @Lock(LockType.WRITE)
    public void removeMobile(MobileDTO mobile) {
        for (MobileDTO m : inventory) {
            if (m.getManufacturer().equals(mobile.getManufacturer())
                    && m.getPrice().equals(mobile.getPrice())
                    && m.getType().equals(mobile.getType())
                    && m.getPiece() >= mobile.getPiece()) {
                if (m.getPiece() < mobile.getPiece()) {
                throw new ItemSoldOutException("Don't have enough of " + mobile.getManufacturer()
                        + " " + mobile.getType());
                }else{
                    m.setPiece(m.getPiece() - mobile.getPiece());
                    return;
                }
            }
        }
        throw new ItemDontExistsException("Don't have " + mobile.getManufacturer()
            + " " + mobile.getType());
    }
}
