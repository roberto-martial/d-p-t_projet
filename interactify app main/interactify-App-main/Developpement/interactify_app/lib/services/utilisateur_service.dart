import 'package:interactify_app/models/utilisateur.dart';
import 'package:interactify_app/repository/utilisateur_repository.dart';

class UtilisateurService {
  UtilisateurRepository utilisateurRepository = UtilisateurRepository();

  Future<List<Utilisateur>> getAllUtilisateur() async {
    return await utilisateurRepository.getAllUtilisateur();
  }

  Future<Utilisateur?> getUserById(String id) async {
    return await utilisateurRepository.getUserById(id);
  }

  Future<void> updateUser(Utilisateur user) async {
    await utilisateurRepository.updateUser(user);
  }
}
