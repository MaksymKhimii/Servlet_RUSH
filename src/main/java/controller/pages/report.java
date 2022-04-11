package controller.pages;

import controller.command.Command;
import db.ReceipsDAO;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

public class report implements Command {
    @Override
    public String execute(HttpServletRequest request) throws SQLException, ClassNotFoundException {
        //TODO сделать обработку и отображение Z-отчета
        String answer = null;
        //TODO сделать методы которые получают суммы за чеки
        // которые были созданны в тот же день. Следовательно
        // нужно получить дату за текущий день и
        // если они совпадают -> прибавить к сумме
        // в отчете+ выставить текущее время


        try {
            //получаем текущую дату
            String Current_Date=
                            ReceipsDAO.GetCurrentDate().get(0)+"-"+
                            ReceipsDAO.GetCurrentDate().get(1)+"-"+
                            ReceipsDAO.GetCurrentDate().get(2)+" "+
                            ReceipsDAO.GetCurrentDate().get(3)+":"+
                            ReceipsDAO.GetCurrentDate().get(4)+":"+
                            ReceipsDAO.GetCurrentDate().get(5);

            ArrayList result = ReceipsDAO.getXSum();
            int countOfReceipts = (int) result.get(0); // колличество чеков в отчете
            int lastIdReceipt = (int) result.get(1);
            double XSum = (double) result.get(2);

            request.setAttribute("currentDate", Current_Date);
            request.setAttribute("countOfReceipts", countOfReceipts);
            request.setAttribute("lastIdReceipt" ,lastIdReceipt);
            request.setAttribute("XSum", XSum);
            //  request.setAttribute("currentDate", currentDate);

            System.out.println("Method: "+result);
            System.out.println("countOfReceipts: "+countOfReceipts);
            System.out.println("lastIdReceipt: "+lastIdReceipt);
            System.out.println("XSum: "+XSum);
        }catch (ParseException e){
            e.printStackTrace();
        }
        answer="/WEB-INF/st_cashier-basic/report.jsp";
        return answer;
    }
}
