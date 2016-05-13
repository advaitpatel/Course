/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Johnny.Dao;

import Johnny.Beans.Tablet;
import Johnny.Common.Constants;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author RZHUANG
 */
public class TabletDao {
    private List<Tablet> tablets = new ArrayList<Tablet>();
    public List<Tablet> getTabletList() {
        if (tablets!=null && tablets.size() > 0) {
            return tablets;
        }

        Tablet ap_ipadpro = new Tablet("ap_ipadpro", Constants.CONST_TABLET_APPLE, "iPad Pro 128GB",949.99,"tablets/ipadpro.jpg",Constants.CONST_TABLET_APPLE,"New",10);
        Tablet ap_ipadair = new Tablet("ap_ipadair", Constants.CONST_TABLET_APPLE, "iPad Air 2 16GB - Gold",399.99,"tablets/ipadair.jpg",Constants.CONST_TABLET_APPLE,"New",10);
        tablets.add(ap_ipadpro);
        tablets.add(ap_ipadair);

        Tablet ms_surface3 = new Tablet("ms_surface3", Constants.CONST_TABLET_MICROSOFT, "Surface 3 - 10.8 128GB Silver",549.99,"tablets/surface3.jpg",Constants.CONST_TABLET_MICROSOFT,"New",10);
        Tablet ms_surface4 = new Tablet("ms_surface4", Constants.CONST_TABLET_MICROSOFT, "Surface 4 12.3 128GB Silver",999.99,"tablets/surface4.jpg",Constants.CONST_TABLET_MICROSOFT,"New",10);
        tablets.add(ms_surface3);
        tablets.add(ms_surface4);

        Tablet ss_galaxya = new Tablet("ss_galaxya", Constants.CONST_TABLET_SAMSUNG, "Galaxy Tab A - 9.7 - 16GB ",299.99,"tablets/galaxya.jpg",Constants.CONST_TABLET_SAMSUNG,"New",10);
        Tablet ss_kidse = new Tablet("ss_kidse", Constants.CONST_TABLET_SAMSUNG, "Kids Galaxy Tab E Lite 7 8GB",129.99,"tablets/kidspad.jpg",Constants.CONST_TABLET_SAMSUNG,"New",10);
        tablets.add(ss_galaxya);
        tablets.add(ss_kidse);    
        
        return tablets;        
    }    
    
    public List<Tablet> getTabletList(String maker) {
        if (maker==null || maker.isEmpty()) {
            return getTabletList();
        }
        if (tablets.size() == 0) {
            getTabletList();
        }
        List<Tablet> res = new ArrayList<Tablet>();
        for(Tablet tablet : tablets) {
            if (tablet.getMaker().toLowerCase().equals(maker.toLowerCase())) {
                res.add(tablet);
            }
        }
        return res;
    }
}
