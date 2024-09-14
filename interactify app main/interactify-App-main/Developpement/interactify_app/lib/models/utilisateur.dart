// ignore_for_file: public_member_api_docs, sort_constructors_first
class Utilisateur {
  String? id;
  String username;
  String promotion;
  String email;
  String? photoProfil;

  Utilisateur({
     this.id,
    required this.username,
    required this.promotion,
    required this.email,
    this.photoProfil,
  });

  static Utilisateur convertFromMap(Map<String, dynamic> map) {
    return Utilisateur(
        id: map["id"] ?? "",
        photoProfil: map["photo"] ?? "assets/images/user.png",
        username: map["username"] ?? "",
        promotion: map["promotion"] ?? "",
        email: map["email"] ?? "");
  }

  static Map<String, dynamic> convertToMap(Utilisateur utilisateur) {
    Map<String, String> utilisateurAsMap;

    utilisateurAsMap = {
      "username": utilisateur.username,
      "promotion": utilisateur.promotion,
      "email": utilisateur.email,
      "photoProfil":  utilisateur.photoProfil!
    };
    if (utilisateur.id != null) {
      utilisateurAsMap["id"] = utilisateur.id!;
    }
    return utilisateurAsMap;
  }

  @override
  String toString() { 
    return 'Utilisateur{id: $id, username: $username, promotion: $promotion, email: $email, photoProfil: $photoProfil}';
  }
}
