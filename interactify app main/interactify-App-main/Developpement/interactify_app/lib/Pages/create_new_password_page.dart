import 'package:flutter/material.dart';
import 'package:interactify_app/Pages/confirm_password_change_page.dart';
import 'package:interactify_app/Pages/forget_password_page.dart';
import 'package:interactify_app/widgets/button_black.dart';
import 'package:interactify_app/widgets/input_password.dart';
import 'package:interactify_app/widgets/input_text.dart';

class CreateNewPasswordPage extends StatelessWidget {
  const CreateNewPasswordPage({super.key});
  static const routeName = "/NewPassword";

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
                Navigator.pushNamed(context, ForgetPasswordPage.routeName);
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
                    Text(
                      "Create new password!",
                      textAlign: TextAlign.start,
                      style: TextStyle(
                        fontSize: 30,
                        
                        fontWeight: FontWeight.bold,
                      ),
                    ),
                    Text(
                      "Your new password must be unique from those previously used.",
                      style: TextStyle(
                        fontSize: 16,
                        color: Colors.grey,
                        fontWeight: FontWeight.w600,
                      ),
                    ),
                  ],
                ),
                Column(
                  mainAxisAlignment: MainAxisAlignment.spaceAround,
                  children: [
                    Padding(
                      padding: const EdgeInsets.only(top: 30),
                      child: InputPassword(
                        hintText: "New Password",
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.symmetric(vertical: 20),
                      child: InputPassword(
                        hintText: "Confirm Password",
                      ),
                    ),
                    Padding(
                      padding: const EdgeInsets.only(top: 20),
                      child: Column(
                        children: [
                          Container(
                              height: 55,
                              child: ButtonBlack(
                                texte: "Reset Password",
                                onPressed: () {
                                  Navigator.pushNamed(context, PasswordConfirmPage.routeName);
                                },
                              )),
                        ],
                      ),
                    ),
                  ],
                ),
              ],
            )),
      ),
    );
  }
}
