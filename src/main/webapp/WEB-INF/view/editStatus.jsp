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


            <h1 class="w-full text-3xl text-black pb-6">Modifica stato</h1>

            <div class="flex flex-wrap">
                <div class="w-full lg:w-1/2 my-6 pr-0 lg:pr-2">
                    <div class="leading-loose">
                        <form class="p-10 bg-white rounded shadow-xl" action="${pageContext.request.contextPath}/editstatus" method="POST">
                            <div class="">
                                <label class="block text-sm text-gray-600" for="numeroCarta">Modifica stato carta</label>
                                <input class="w-full px-5 py-1 text-gray-700 bg-gray-200 rounded" id="numeroCarta" name="numeroCarta" type="text" required="" placeholder="Inserisci numero carta" aria-label="Name">
                            </div>
                            <div class="mt-6">
                                <button class="px-4 py-1 text-white font-light tracking-wider bg-gray-900 rounded" type="submit">Modifica stato</button>
                            </div>
                        </form>
                        <%
                            if(request.getAttribute("message")!=null) {
                                String message = request.getAttribute("message").toString();
                                String[] arrSplit = message.split(",");
                                out.println("Carta numero: "+ arrSplit[0]);
                                out.println("Attiva: "+ arrSplit[1]);

                            }
                        %>
                    </div>
                </div>

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
