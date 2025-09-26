import 'dart:convert';
import 'package:http/http.dart' as http;

Future<void> main() async {
  const String baseUrl = 'https://pizza-rest-server-production.up.railway.app/api/pizzeria/pizzes';
  const int pageSize = 4;

  List<Future<http.Response>> peticiones = [
    http.get(Uri.parse('$baseUrl?pageNumber=1&pageSize=$pageSize')),
    http.get(Uri.parse('$baseUrl?pageNumber=2&pageSize=$pageSize')),
    http.get(Uri.parse('$baseUrl?pageNumber=3&pageSize=$pageSize')),
    http.get(Uri.parse('$baseUrl?pageNumber=4&pageSize=$pageSize')),
  ];

  List<http.Response> respuestas = await Future.wait(peticiones);

  List<Map<String, dynamic>> todasLasPizzas = [];

  for (var response in respuestas) {
    if (response.statusCode == 200) {
      Map<String, dynamic> data = jsonDecode(response.body);
      List<dynamic> records = data['records'];

      for (var record in records) {
        todasLasPizzas.add({
          'nom': record['nom'],
          'preu': record['preu'],
        });
      }
    } else {
      print('Error en la petición: ${response.statusCode}');
    }
  }

  print('Todas las pizzas:');
  for (var pizza in todasLasPizzas) {
    print('${pizza['nom']} - \$${pizza['preu'].toStringAsFixed(2)}');
  }
}
