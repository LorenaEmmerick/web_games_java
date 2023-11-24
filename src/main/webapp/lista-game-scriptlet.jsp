<%@ page import="java.net.URL, java.net.HttpURLConnection, java.io.*" %>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <title>Buscar Game</title>
</head>
    <body>
       <table>
            <%
                URL url = new URL("http://localhost:9090/games/find/452");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                InputStream inputStream = connection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

                String item;
                StringBuilder json = new StringBuilder();

                    while ((item = bufferedReader.readLine()) != null) {
                          json.append(item);
                    }

            %>
            <tr>
                <td>
                    <%
                        out.println("Dados do Game: " + json.toString());
                     %>
                </td>
            </tr>
        </table>
    </body>
</html>