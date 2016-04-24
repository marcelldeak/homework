

package xyz.codingmentor.building.jpa;

import xyz.codingmentor.building.jpa.dto.Address;
import xyz.codingmentor.building.jpa.type.RestaurantType;
import xyz.codingmentor.building.jpa.entity.Owner;
import xyz.codingmentor.building.jpa.entity.Restaurant;
import xyz.codingmentor.building.jpa.entity.Office;
import xyz.codingmentor.building.jpa.entity.Building;
import xyz.codingmentor.building.jpa.entity.House;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.jboss.logging.Logger;


public class Main {
    
    private static final Logger LOGGER = Logger.getLogger("MainLogger");
    
    private static final String BUDAPEST = "Budapest";
    
    private Main(){
    }
    
    public static void main(String[] args) {
        
        Address addr1 = new Address("1031",BUDAPEST,"Őrő utca");
        Address addr2 = new Address("4526","Kecskemét","Huba tér");
        Address addr3 = new Address("1163",BUDAPEST,"Sugár köz");
        Address addr4 = new Address("6532","Mosonmagyaróvár","Vízpart utca");
        Address addr5 = new Address("2388","Szeged","Huszár út");
        Address addr6 = new Address("1084",BUDAPEST,"Kő utca");
        Address addr7 = new Address("7231","Kőrős","Ó utca");
        Address addr8 = new Address("6345","Velence","Tó utca");
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(1976, 3, 5);
        Date date = calendar.getTime();
        Owner owner1 = new Owner("Mr","owner1",date);
        
        calendar.set(1993, 2, 21);
        date = calendar.getTime();
        Owner owner2 = new Owner("Mrs","owner2",date);
        
        calendar.set(1965, 11, 13);
        date = calendar.getTime();
        Owner owner3 = new Owner("Dr","owner3",date);
        
        calendar.set(1984, 5, 28);
        date = calendar.getTime();
        Owner owner4 = new Owner("Sir","owner4",date);
                
        House home1 = new House(addr1,"home1",5, owner1, 3, 90.6);
        House home2 = new House(addr2,"home2",3, owner2, 2, 88.45);
        House home3 = new House(addr3,"home3",3, owner3, 6, 79.23);
        House home4 = new House(addr6,"home4",2, owner4, 1, 94.87);
        
        House house1 = new House(addr4,"house1",4,owner2,4,92.34);
        House house2 = new House(addr7,"house2",1,owner4,0,89.4);
        House house3 = new House(addr8,"house3",2,owner4,2,75.69);
        
        Restaurant rest1 = new Restaurant(true,15,RestaurantType.ALACARTE, addr4, "restaurant1", 5, owner1);
        Restaurant rest2 = new Restaurant(true,3,RestaurantType.BUFE, addr3, "restaurant2", 2, owner1);
        Restaurant rest3 = new Restaurant(false,0,RestaurantType.FASTFOOD, addr7, "restaurant3", 3, null);
        
        Office office1 = new Office(3, Boolean.TRUE, addr5, "office1", 40, owner3);
        Office office2 = new Office(1, Boolean.FALSE, addr2, "office2", 10, owner3);
        Office office3 = new Office(0, Boolean.FALSE, addr4, "office3", 23, null);
        
        owner1.addOwnedBuildings(home1,rest1,rest2);
        owner2.addOwnedBuildings(home2,house1);
        owner3.addOwnedBuildings(home3,office1);
        owner4.addOwnedBuildings(home4,house2,house3);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("building-jpa-persistence");
        EntityManager em = emf.createEntityManager();
        EntityTransaction trs = em.getTransaction();
        
        TypedQuery<Owner> ownerQuery = em.createNamedQuery("officeOwners", Owner.class);
        TypedQuery<Building> buildingQuery = em.createNamedQuery("buildingsFromBudapest", Building.class);
        
        List<Building> buildingQueryResult;
        List<Owner> ownerQueryResult;
        
        trs.begin();
        
        em.persist(owner1);
        em.persist(owner2);
        em.persist(owner3);
        em.persist(owner4);
        
        em.persist(home1);
        em.persist(home2);
        em.persist(home3);
        em.persist(home4);
        
        em.persist(house1);
        em.persist(house2);
        em.persist(house3);
        
        em.persist(rest1);
        em.persist(rest2);
        em.persist(rest3);
        
        em.persist(office1);
        em.persist(office2);
        em.persist(office3);
        
        trs.commit();
        
        trs.begin();
        ownerQueryResult = ownerQuery.getResultList();
        buildingQueryResult = buildingQuery.getResultList();
        trs.commit();
        
        LOGGER.info(ownerQueryResult);
        LOGGER.info(buildingQueryResult);
        
        buildingQuery = em.createNamedQuery("smallHouses", Building.class);
        trs.begin();
        buildingQueryResult = buildingQuery.getResultList();
        trs.commit();

        LOGGER.info(buildingQueryResult);
        
        buildingQuery = em.createNamedQuery("findBuildingByName", Building.class);
        buildingQuery.setParameter("name", "home_");
        trs.begin();
        buildingQueryResult = buildingQuery.getResultList();
        trs.commit();
        
        LOGGER.info(buildingQueryResult);
        
        TypedQuery<Address> addressQuery = em.createNamedQuery("addressesOfBufes", Address.class);
        List<Address> addressQueryResult;
        trs.begin();
        addressQueryResult= addressQuery.getResultList();
        trs.commit();
        
        LOGGER.info(addressQueryResult);
        
        em.close();
        emf.close();
    }
    
}
