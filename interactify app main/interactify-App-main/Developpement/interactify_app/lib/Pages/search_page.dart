import 'package:flutter/material.dart';
import 'package:interactify_app/models/Users.dart';
import 'package:interactify_app/models/utilisateur.dart';
import 'package:interactify_app/services/user_service.dart';
import 'package:interactify_app/services/utilisateur_service.dart';
import 'package:interactify_app/widgets/nav_bar.dart';
import 'package:interactify_app/widgets/search_bar.dart';
import 'package:interactify_app/widgets/search_element_row.dart';

class SearchPage extends StatefulWidget {
  static const routeName = "/search";
  SearchPage({super.key});

  @override
  _SearchPageState createState() => _SearchPageState();
}

class _SearchPageState extends State<SearchPage> {
  // final userService = UserService();
  final UtilisateurService utilisateurService = UtilisateurService();
  final TextEditingController _searchController = TextEditingController();
  List<Utilisateur> allUsers = [];
  List<SearchElement> searchelements = [];
  List<SearchElement> foundElements = [];

  @override
  void initState() {
    super.initState();
    fetchUsers();
  }

  void fetchUsers() async {
    List<Utilisateur> utilisateurs =
        await utilisateurService.getAllUtilisateur();
    setState(() {
      allUsers = utilisateurs;
      if (allUsers.isNotEmpty) {
      for (var user in allUsers) {
        searchelements.add(SearchElement(user: user));
        foundElements = searchelements;
      }
    } 
    });
  }

  void _runFilter(String value) {
    List<SearchElement> results = [];
    results = searchelements;
    if (value.isEmpty) {
      results = searchelements;
    } else {
      results = searchelements
          .where((searchElement) => searchElement.user.username
              .toLowerCase()
              .contains(value.toLowerCase()))
          .toList();
    }
    setState(() {
      foundElements = results;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      body: Column(
        children: [
          Padding(
            padding: EdgeInsets.symmetric(horizontal: 15),
            child: SearchingBar(
                controllerTextEditing: _searchController,
                runFilter: _runFilter),
          ),
          Expanded(
            child: ListView.builder(
              itemCount: foundElements.length,
              itemBuilder: (context, index) {
                return foundElements[index];
              },
            ),
          ),
        ],
      ),
      bottomNavigationBar: NavBar(),
    );
  }
}
