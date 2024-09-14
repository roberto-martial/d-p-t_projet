import 'package:flutter/material.dart';
import 'package:interactify_app/models/on_boarding.dart';
import 'package:interactify_app/widgets/on_board_card.dart';
import 'package:flutter_gen/gen_l10n/app_localizations.dart';

class OnBoardingPage extends StatelessWidget {
  static final PageController _pageController = PageController(initialPage: 0);
  static const routeName = "/onBoardingPage";
  OnBoardingPage({super.key});

  @override
  Widget build(BuildContext context) {
    final localizations = AppLocalizations.of(context)!;

    final OnBoarding onBoarding_page1 = OnBoarding(
        title: localizations.onBoarding_page1,
        path: "assets/images/rafiki.png",
        pageController: _pageController);
    final OnBoarding onBoarding_page2 = OnBoarding(
        title: localizations.onBoarding_page2,
        path: "assets/images/bro.png",
        pageController: _pageController);
    final OnBoarding onBoarding_page3 = OnBoarding(
        title: localizations.onBoarding_page3,
        path: "assets/images/echelle.png",
        pageController: _pageController);

    List<Widget> _onBoardingCard = [
      OnBoardCard(onBoarding: onBoarding_page1),
      OnBoardCard(onBoarding: onBoarding_page2),
      OnBoardCard(onBoarding: onBoarding_page3)
    ];

    return Scaffold(
        body: Padding(
      padding: const EdgeInsets.symmetric(vertical: 30),
      child: Column(
        mainAxisSize: MainAxisSize.max,
        mainAxisAlignment: MainAxisAlignment.center,
        crossAxisAlignment: CrossAxisAlignment.center,
        children: [
          Expanded(
              child: PageView(
                  controller: _pageController, children: _onBoardingCard)),
        ],
      ),
    ));
  }
}
