import com.sg.VendingMachine.Controller.Controller;
import com.sg.VendingMachine.dao.*;
import com.sg.VendingMachine.dto.Item;
import com.sg.VendingMachine.service.VendingMachineInsufficientFundsException;
import com.sg.VendingMachine.service.VendingMachineNoItemInventoryException;
import com.sg.VendingMachine.service.VendingMachineService;
import com.sg.VendingMachine.service.VendingMachineServiceImpl;
import com.sg.VendingMachine.ui.VendingMachineUserIO;
import com.sg.VendingMachine.ui.VendingMachineUserIOImpl;
import com.sg.VendingMachine.ui.VendingMachineView;

import java.math.BigDecimal;

public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        VendingMachineView myView = new VendingMachineView();
        VendingMachineAuditDao obj = new VendingMachineAuditDaoImpl();
        VendingMachineService myService = new VendingMachineServiceImpl(myDao, obj);
        Controller myController = new Controller(myView, myService);
        myController.run();
    }
}