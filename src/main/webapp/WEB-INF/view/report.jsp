<%@ page import="com.musso.megliodellavisa.model.Transazioni" %>
<%@ page import="java.util.ArrayList" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Meglio della visa</title>
    <meta name="author" content="David Grzyb">
    <meta name="description" content="">

    <!-- Tailwind -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css" rel="stylesheet">
    <style>
        @import url('https://fonts.googleapis.com/css?family=Karla:400,700&display=swap');
        .font-family-karla { font-family: karla; }
        .bg-sidebar { background: #3d68ff; }
        .cta-btn { color: #3d68ff; }
        .upgrade-btn { background: #1947ee; }
        .upgrade-btn:hover { background: #0038fd; }
        .active-nav-link { background: #1947ee; }
        .nav-item:hover { background: #1947ee; }
        .account-link:hover { background: #3d68ff; }
    </style>
</head>
<body class="bg-gray-100 font-family-karla flex">

<jsp:include page="../componenti/lateralNavbar.jsp"></jsp:include>


<div class="relative w-full flex flex-col h-screen overflow-y-hidden">


    <div class="w-full h-screen overflow-x-hidden border-t flex flex-col">
        <main class="w-full flex-grow p-6">
            <h1 class="text-3xl text-black pb-6">Report</h1>

            <div class="w-full mt-6">
                <p class="text-xl pb-3 flex items-center">
                    <i class="fas fa-list mr-3"></i> Report utente
                </p>
                <div class="bg-white overflow-auto">
                    <table class="min-w-full bg-white">
                        <thead class="bg-gray-800 text-white">
                        <tr>
                            <th class="w-1/3 text-left py-3 px-4 uppercase font-semibold text-sm">Numero carta</th>
                            <th class="w-1/3 text-left py-3 px-4 uppercase font-semibold text-sm">Operazione</th>
                            <th class="text-left py-3 px-4 uppercase font-semibold text-sm">Data ed ora</th>
                        </tr>
                        </thead>
                        <tbody class="text-gray-700">
                        <%--
                        Provato ad usare i cicli presenti nelle jsp ma non sono riuscito a far andare
                        <c:forEach items="${transazioni}" var="item" varStatus="i">
                            <c:set var="rowClass" value="${i.index % 2 == 0 ? '' : 'bg-gray-200'}" />
                            <tr class="${rowClass}">
                                <td class="w-1/3 text-left py-3 px-4"><c:out value = "${item.getNumeroCarta()}"/></td>
                                <td class="w-1/3 text-left py-3 px-4"><c:out value = "${item.getOperazione()}"/></td>
                                <td class="w-1/3 text-left py-3 px-4"><c:out value = "${item.getDate()}"/></td>
                            </tr>
                        </c:forEach>
                        --%>

                        <%
                            ArrayList<Transazioni> list = (ArrayList<Transazioni>) request.getAttribute("transazioni");

                            for(int i=0; i < list.size(); i++) {
                                if (i % 2 == 0){
                                    out.println("<tr class=\"bg-gray-200\">" +
                                            "<td class=\"w-1/3 text-left py-3 px-4\"> "+ list.get(i).getNumeroCarta()  + "  </td>" +
                                            "<td class=\"w-1/3 text-left py-3 px-4\"> "+  list.get(i).getOperazione()  + "  </td>" +
                                            "<td class=\"w-1/3 text-left py-3 px-4\"> "+  list.get(i).getDate()        + "  </td>" +
                                            "</tr> " );
                                }
                                else{
                                    out.println("<tr> " +
                                            "<td class=\"w-1/3 text-left py-3 px-4\"> "+ list.get(i).getNumeroCarta()  + "  </td>" +
                                            "<td class=\"w-1/3 text-left py-3 px-4\"> "+  list.get(i).getOperazione()  + "  </td>" +
                                            "<td class=\"w-1/3 text-left py-3 px-4\"> "+  list.get(i).getDate()        + "  </td>" +
                                            "</tr> " );
                                }
                            }
                        %>
                        </tbody>
                    </table>

                </div>

            </div>
            <input type="button" value="Esporta come PDF" class="px-2 py-1 text-white font-light tracking-wider bg-gray-900 rounded" onClick="window.print()">
        </main>

        <footer class="w-full bg-white text-right p-4">
            Built by <a target="_blank" href="https://davidgrzyb.com" class="underline">David Grzyb</a>.
        </footer>
    </div>

</div>

<!-- AlpineJS -->
<script src="https://cdn.jsdelivr.net/gh/alpinejs/alpine@v2.x.x/dist/alpine.min.js" defer></script>
<!-- Font Awesome -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/js/all.min.js" integrity="sha256-KzZiKy0DWYsnwMF+X1DvQngQ2/FxF7MF3Ff72XcpuPs=" crossorigin="anonymous"></script>
</body>
</html>




