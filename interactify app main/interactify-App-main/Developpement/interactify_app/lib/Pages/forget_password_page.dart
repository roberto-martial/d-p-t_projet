import 'package:flutter/gestures.dart';
import 'package:flutter/material.dart';
import 'package:interactify_app/Pages/confirm_password_change_page.dart';
import 'package:interactify_app/Pages/create_new_password_page.dart';
import 'package:interactify_app/Pages/login_page.dart';
import 'package:interactify_app/Pages/login_register_page.dart';
import 'package:interactify_app/services/auth_service.dart';
import 'package:interactify_app/widgets/button_black.dart';
import 'package:interactify_app/widgets/input_text.dart';

class ForgetPasswordPage extends StatefulWidget {
  static const routeName = "/forgetPassword";

  const ForgetPasswordPage({super.key});

  @override
  State<ForgetPasswordPage> createState() => _ForgetPasswordPageState();
}

class _ForgetPasswordPageState extends State<ForgetPasswordPage> {
  final _email = TextEditingController();
  final _authService = AuthService();
  Widget build(BuildContext context) {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 18),
      child: Scaffold(
        appBar: AppBar(
          leading: Container(
            padding: const EdgeInsets.symmetric(vertical: 10),
            decoration: BoxDecoration(
              border: Border.all(
                color: Colors.grey, // Color of the border
                width: 0.3, // Width of the border
              ),
              borderRadius: BorderRadius.circular(8), // Rounded corners
            ),
            child: IconButton(
              hoverColor: Colors.transparent,
              icon: Icon(
                Icons.arrow_back_ios_new_rounded,
                color: Color(0xFF051C24),
              ),
              onPressed: () {
                Navigator.pushNamed(context, LoginRegisterPage.routeName);
              },
            ),
          ),
          elevation: 0,
          backgroundColor: Colors.transparent,
        ),
        body: Padding(
            padding: const EdgeInsets.only(top: 30, bottom: 10),
            child: Column(
              crossAxisAlignment: CrossAxisAlignment.start,
              // mainAxisAlignment: MainAxisAlignment.spaceBetween,
              children: [
                Column(
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(bottom: 15),
                      child: Text(
                        "Welcome back! Glad to see you, Again!",
                        style: TextStyle(
                          fontSize: 30,
                          fontWeight: FontWeight.bold,
                        ),
                      ),
                    ),
                    Text(
                      "Don't worry! It occurs. Please enter the email address linked with your account.",
                      style: TextStyle(
                        fontSize: 16,
                        color: Colors.grey,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                  ],
                ),
                Expanded(
                  flex: 1,
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.spaceAround,
                    children: [
                      Padding(
                        padding: const EdgeInsets.symmetric(vertical: 0),
                        child: InputText(
                          controller: _email,
                          hintText: "Enter your Email",
                        ),
                      ),
                      Padding(
                        padding: const EdgeInsets.only(top: 0),
                        child: Column(
                          children: [
                            Container(
                                height: 55,
                                child: ButtonBlack(
                                  texte: "Send Code",
                                  onPressed: () async {
                                    await _authService
                                        .sendPasswordResetLink(_email.text);

                                    ScaffoldMessenger.of(context).showSnackBar(
                                        SnackBar(
                                            content: Text(
                                                "Email reset password sent")));
                                    Navigator.pushNamed(context,
                                        PasswordConfirmPage.routeName);
                                  },
                                )),
                          ],
                        ),
                      ),
                    ],
                  ),
                ),
                Expanded(
                  flex: 2,
                  child: Row(
                    mainAxisAlignment: MainAxisAlignment.center,
                    crossAxisAlignment: CrossAxisAlignment.end,
                    children: [
                      Padding(
                        padding: const EdgeInsets.only(bottom: 20),
                        child: RichText(
                          text: TextSpan(
                            text: "Don’t have an account? ",
                            style: TextStyle(
                              color: Colors.black,
                              fontSize: 16,
                            ),
                            children: [
                              TextSpan(
                                text: "Login",
                                style: TextStyle(
                                  color: Colors.blue,
                                  fontSize: 16,
                                  fontWeight: FontWeight.w600,
                                ),
                                recognizer: TapGestureRecognizer()
                                  ..onTap = () {
                                    Navigator.pushNamed(
                                        context, LoginPage.routeName);
                                  },
                              ),
                            ],
                          ),
                        ),
                      ),
                    ],
                  ),
                ),
              ],
            )),
      ),
    );
  }
}
