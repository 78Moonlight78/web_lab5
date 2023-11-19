import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;
import java.util.ResourceBundle;
import jakarta.servlet.ServletException;
//import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

//@WebServlet("/TeamList")
/**
 * Servlet implementation class Testing
 */
public class Testing extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Object[][] team;
    private String[] roles;
    ResourceBundle res;
    String salary, lang;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Testing() {
        super();
        team = new Object[][] {
                {"Иванов Антон",  "Москва", 1500},
                {"Петрова Алла",  "Санкт-Петербург", 3000},
                {"Антонов Алексей",  "Красноярск", 2500},
                {"Смирнова Валерия", "Краснодар", 1900}
        };

    }
    /**
     * Processes requests for both HTTP <code>GET</code> and
     <code>POST</code>
     methods.
     *
     4
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse
                                          response)
            throws ServletException, IOException {
        String lang = request.getParameter("lang");
        if(lang == null) {            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
                "Ожидался параметр lang");
            return;
        }
        if(!"en".equalsIgnoreCase(lang) && !"ru".equalsIgnoreCase(lang)) {
            response.sendError(HttpServletResponse.SC_NOT_ACCEPTABLE,
                    "Параметр lang может принимать значения en или ru");
            return;
        }
        // Задание типа содержимого для ответа (в том числе кодировки)
        response.setContentType("text/html;charset=UTF-8");
        // Файлы ресурсов book.properties, book_en.properties и book_ru.properties
        // Установка локализации в соответствии с выбором пользователя
        ResourceBundle res = ResourceBundle.getBundle(
                "mag", "en".equalsIgnoreCase(lang) ? Locale.ENGLISH : Locale.getDefault());
        // Получение потока для вывода ответа
        PrintWriter out = response.getWriter();
        try {
// Создание HTML-страницы
            out.println("<html>");
            out.println("<head><title>Список Фокусников</title></head>");
            out.println("<body>");
            out.println("<h1>"+res.getString("title") + ((salary ==
                    null)? " ": " с зарплатой >= "
                    + salary + "$") + "</h1>");
            out.println("<table border='1'>");
            out.println("<tr><td><b>" + res.getString("name") +
                    "</b></td>"
                    + "<td><b>" + res.getString("city") +
                    "</b></td>"
                    + "<td><b>" + res.getString("salary") +
                    "</b></td></tr>");
            for (Object[] temp : team)
                if (salary == null || (int)temp[2] >=
                        Integer.parseInt(salary))
                    out.println("<tr><td>" + temp[0] +
                            "</td><td>"
                            + temp[1] + "</td><td>" +
                            Integer.toString((int)temp[2]) + "</td></tr>");
            out.println("</table>");
            out.println("</body>");
            out.println("</html>");
        } finally {
// Закрытие потока вывода
            out.close();
        }
    }
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    /**
     * Handles the HTTP
     * <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}
