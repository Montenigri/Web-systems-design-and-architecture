<%@ page import="com.musso.megliodellavisa.model.Utente" %>
<%@ page import="java.util.Objects" %>

<aside class="relative bg-sidebar h-screen w-64 hidden sm:block shadow-xl">
    <div class="p-6">
        <a href="/" class="text-white text-3xl font-semibold uppercase hover:text-gray-300">Meglio della visa</a>
    </div>
    <nav class="text-white text-base font-semibold pt-3">
        <a href="/" class="flex items-center text-white opacity-75 hover:opacity-100 py-4 pl-6 nav-item">
            <i class="fas fa-tachometer-alt mr-3"></i>
            Home
        </a>
        <a href="/creditoresiduo" class="flex items-center text-white opacity-75 hover:opacity-100 py-4 pl-6 nav-item">
            <i class="fas fa-sticky-note mr-3"></i>
            Verifica credito
        </a>
        <%
            Utente utente = (Utente) session.getAttribute("currentUser");
            if (utente!=null) {
                String ruolo = utente.getRuolo();
                if(Objects.equals(ruolo, "admin")) {
                        out.println(
                                " <a href=\"/cambiaruolo\" class=\"flex items-center text-white opacity-75 hover:opacity-100 py-4 pl-6 nav-item\">\n" +
                                        "            <i class=\"fas fa-tablet-alt mr-3\"></i>\n" +
                                        "            Modifica ruolo utente\n" +
                                        "        </a>\n" +
                                        "        <a href=\"/editstatus\" class=\"flex items-center text-white opacity-75 hover:opacity-100 py-4 pl-6 nav-item\">\n" +
                                        "            <i class=\"fas fa-tablet-alt mr-3\"></i>\n" +
                                        "            Modifica stato carta\n" +
                                        "        </a>\n" +
                                        "        <a href=\"/viewstatus\" class=\"flex items-center text-white opacity-75 hover:opacity-100 py-4 pl-6 nav-item\">\n" +
                                        "            <i class=\"fas fa-tablet-alt mr-3\"></i>\n" +
                                        "            verifica stato carta\n" +
                                        "        </a>\n "
                        );
                }
                if(Objects.equals(ruolo, "admin")||Objects.equals(ruolo, "negoziante")) {
                        out.println(
                                        "        <a href=\"/report\" class=\"flex items-center text-white opacity-75 hover:opacity-100 py-4 pl-6 nav-item\">\n" +
                                        "            <i class=\"fas fa-calendar mr-3\"></i>\n" +
                                        "            Report\n" +
                                        "        </a>\n" +
                                        "        <a href=\"/editcredito\" class=\"flex items-center text-white opacity-75 hover:opacity-100 py-4 pl-6 nav-item \">\n" +
                                        "            <i class=\"fas fa-align-left mr-3\"></i>\n" +
                                        "            Effettua operazione\n" +
                                        "        </a> "
                        );
                }

                        out.println("<a href=\"/logout\" class=\"flex items-center text-white opacity-75 hover:opacity-100 py-4 pl-6 nav-item\">\n" +
                                "            <i class=\"fas fa-calendar mr-3\"></i>\n" +
                                "            Logout\n" +
                                "        </a>");

            }

        %>
    </nav>

</aside>