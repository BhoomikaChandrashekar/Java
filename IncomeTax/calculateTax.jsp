<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TaxCalculation</title>
    </head>
    <body>
        <%
            String name=request.getParameter("name");
            String gen=request.getParameter("gen");
            String place=request.getParameter("work");
            float income=Float.parseFloat(request.getParameter("income"));
            String fullname="";
            String howTaxCal="";
            if(gen.equals("M"))
            {
                fullname="Mr. "+name;
            }
            else
            {
                fullname="Ms. "+name;
            }
            double tax=0;
            if(income<=100000)
            {
                howTaxCal="Income is below 100000, no tax to be paid";
                tax=0;
            }
            else if(income>=100001 && income<=500000)
            {
                howTaxCal="Tax to be pid is 0 upto 1Lakh and 15% of the income for rest of the income";
                tax=0.15*(income-100000);
            }
            else if(income>500001)
            {
                howTaxCal="Tax to be pid is 0 upto 1Lakh and 15% of the income upto 5Lakhs and 20% of the income for remaining income amount";
                tax=0.15*40000 + 0.2*(income-500000);
            }
            String n="Name : "+name;
            String gender="Gender : "+gen;
            String work="Place of Work : "+place;
            String inc="Income : "+income;
            String taxpaid="Tax to be paid is : "+tax;
            %>
            
            <jsp:forward page="dataCapture.jsp">
                        <jsp:param name="Detail" value="<%=fullname%>"/>
                        <jsp:param name="Details" value="<%=n%>"/>
                        <jsp:param name="Details1" value="<%=gender%>"/>
                        <jsp:param name="Details2" value="<%=work%>"/>
                        <jsp:param name="Details3" value="<%=inc%>"/>
                        <jsp:param name="Details4" value="<%=taxpaid%>"/>
                        <jsp:param name="Details5" value="<%=howTaxCal%>"/>
            </jsp:forward>
            
            
         
    </body>
</html>
