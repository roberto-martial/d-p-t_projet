
class Users {
  String id;
  String username;
  String promotion;
  String email;
  String? photo;

  Users({
    required this.id,
    required this.username,
    required this.promotion,
    required this.email,
    this.photo,
  });

  factory Users.fromJson(Map<String, dynamic> json) {
    return Users(
      id: json['id'],
      username: json['username'],
      promotion: json['promotion'],
      email: json['email'],
      photo: json['photo'] ?? ' ',
    );
  }

  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'username': username,
      'promotion': promotion,
      'email': email,
      'photo': photo,
    };
  }
}
