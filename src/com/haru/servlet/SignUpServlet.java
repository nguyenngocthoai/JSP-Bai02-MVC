package com.haru.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.haru.dao.PersonDao;
import com.haru.entities.Person;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/signup")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SignUpServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		request.getRequestDispatcher("/").forward(request, response);
		response.sendRedirect(request.getContextPath()+"/person");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("do post");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String reemail = request.getParameter("reemail");
		String password = request.getParameter("password");
		String day = request.getParameter("day");
		String month = request.getParameter("month");
		String year = request.getParameter("year");
		String birthday = day + "-" + month + "-" + year;
		String gender = request.getParameter("gender");
		if (!email.equalsIgnoreCase(reemail)) {
			request.setAttribute("msg", "Email không khớp!");
		}else {
			Person person = new Person(firstname, lastname, email, password, birthday, gender);
			PersonDao personDao = new PersonDao();
			personDao.savePerson(person);
//			List<Person> persons = personDao.getAllPersons();
//			request.setAttribute("persons", persons);
			response.sendRedirect(request.getContextPath()+"/person");
//			request.getRequestDispatcher("/").forward(request, response);
//			getServletContext().getRequestDispatcher("/").forward(request, response);
		}	
//		doGet(request, response);
	}

}
