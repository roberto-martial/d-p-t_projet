using KitchenRoom1.contract;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace KitchenRoom1.model
{
    public class KitchenModel
    {
        public List<IObserver> observers;

        public KitchenRoom kitchenRoom { get; set; }

        public KitchenMaterial cookingFire;

        public KitchenMaterial oven;

        public KitchenMaterial blender;

        public KitchenMaterial pan;

        public KitchenMaterial kitchenKnife;

        public Recipe[] recipes { get; set; }

    
        public Chef[] chefs { get; set; }

        
        public PartChef[] partChefs { get; set; }

       
        public KitchenClerk[] clerks { get; set; }

        
        public Washer[] washers { get; set; }


        public KitchenModel()
        {
            kitchenRoom = new KitchenRoom();
            observers = new List<IObserver>();


            cookingFire = KitchenMaterialFactory.CreateCookingFire();
            oven = KitchenMaterialFactory.CreateOven();
            blender = KitchenMaterialFactory.CreateBlender();
            pan = KitchenMaterialFactory.CreatePan();
            kitchenKnife = KitchenMaterialFactory.CreateKitchenKnife();

            recipes = new Recipe[7]
            {
                new Recipe
                (
                    "Feuilleté au crabe",

                    new Ingredient[4]
                    {
                        new Ingredient("pâte feuilletée"),
                        new Ingredient("oeuf"),
                        new Ingredient("sel"),
                        new Ingredient("poivre")
                    },

                    new RecipeTask[3]
                    {
                        new RecipeTask("Préchauffer le four à 230°", 5, oven),
                        new RecipeTask("Mélanger crabe, citron, chapelure, herbes", 1, blender),
                        new RecipeTask("Lier le tout avec un oeuf", 1, kitchenKnife)
                    }
                ),

                new Recipe
                (
                    "Œufs cocotte",

                    new Ingredient[5]
                    {
                        new Ingredient("1 œuf par personne"),
                        new Ingredient("1 sachet de gruyère râpé"),
                        new Ingredient("sel"),
                        new Ingredient("Crème fraîche"),
                        new Ingredient("Poivre")
                    },

                    new RecipeTask[5]
                    {
                        new RecipeTask("Séparer les blancs des jaunes et laisser ces derniers dans une demi-coquille.", 5, oven),
                        new RecipeTask("Monter les blancs en neige et incorporer un sachet de râpé.", 1, blender),
                        new RecipeTask("Lier le tout avec un oeuf", 1, kitchenKnife),
                        new RecipeTask("Rajouter un peu de crème fraîche sur le jaune, poivrer et saler..", 5, oven),
                        new RecipeTask("Enfourner 5 minutes à four chaud (surveiller la cuisson)", 1, blender)
                    }
                ),

                new Recipe
                (
                    "Bouillinade d’anguilles ou de poissons",

                    new Ingredient[4]
                    {
                        new Ingredient("Lotte, congre, rouget grondin"),
                        new Ingredient(" seiche, petit crabe, ail, farine, saindoux,"),
                        new Ingredient("sel, poivre"),
                        new Ingredient("piment et pommes de terre")
                    },

                    new RecipeTask[4]
                    {
                        new RecipeTask("Enduire la cocotte de saindoux.", 5, oven),
                        new RecipeTask("Dans le fond y déposer 6 ou 7 petits crabes, couvrir d’une couche de pommes de terre tranchées, une poignée d’ail tranché, sel, poivre et quelques morceaux de saindoux.", 1, blender),
                        new RecipeTask(" Mettre à présent une petite couche de farine, disposer tout le poisson au-dessus et ajouter de l’eau jusqu’au niveau de la dernière couche de pommes de terre. Attendre le premier bouillon et rajouter un filet d’huile", 1, kitchenKnife),
                        new RecipeTask(". Laisser cuire à gros bouillons 20 mn. Accompagner de tranches de pain grillé frotté à l’ail", 5, oven),
                    }
                ),


                   new Recipe
                (
                    "Pâté de sanglier cliente Christian",

                    new Ingredient[4]
                    {
                        new Ingredient("Pour 2 kg de sanglier, 1 kg de ventrèche, 1 kg de chair à saucisse,"),
                        new Ingredient(" 1 kg de gorge de porc ou de lard."),
                        new Ingredient("Sel 50 gr par kg ( 1 c à c = 5 gr)"),
                        new Ingredient("Poivre 6 gr par kg")
                    },

                    new RecipeTask[4]
                    {
                        new RecipeTask("Hacher les viandes puis bien mélanger le tout. Parfumer (truffes, herbes etc….).", 5, oven),
                        new RecipeTask("puis remplir les bocaux en tassant un peu. Faire cuire les bocaux au four au bain marie pendant 2 heures puis les retirer immédiatement, les fermer.", 1, blender),
                        new RecipeTask(" les placer dans un couscoussier rempli d’eau chaude avec un torchon au fond puis des pierres dessus et faire cuire encore 1 heure ½.", 1, kitchenKnife),
                        new RecipeTask(" Les laisser dans le couscoussier jusqu’à refroidissement total.", 5, oven),
                    }
                ),

                     new Recipe
                (
                    "crepes",

                    new Ingredient[4]
                    {
                        new Ingredient("500 gr de farine"),
                        new Ingredient("250 gr de sucre"),
                        new Ingredient("1 litre de lait"),
                        new Ingredient("5 œufs")
                    },

                    new RecipeTask[1]
                    {
                        new RecipeTask("Cuire à la crépière,", 5, oven)
                        
                    }
                ),



                      new Recipe
                (
                    "Tarte au thon",

                    new Ingredient[8]
                    {
                        new Ingredient("50 gr de farine  "),
                        new Ingredient("1/2 litre de lait "),
                        new Ingredient("1/2 boite de champignons de paris"),
                        new Ingredient("1 boite de thon"),
                        new Ingredient("gruyère rapé"),
                        new Ingredient("1 pate feuilletée"),
                        new Ingredient("30gr de beurre"),
                        new Ingredient("Sel et poivre")
                    },

                    new RecipeTask[4]
                    {
                        new RecipeTask("Etaler la pate dans un moule, Faire fondre le beurre, y ajouter la farine et le lait petit", 5, oven),
                        new RecipeTask("à petit, Bien mélanger à l'aide d'un fouet jusqu’à épaississement, saler et poivrer", 5, oven),
                        new RecipeTask("Egoutter le thon et les champignons puis les ajouter à la béchamelle,", 5, oven),
                        new RecipeTask(", Mettre l'appareil sur la pate et parsemer de gruyère, Mettre au four 20mn.", 5, oven)
                            

                    }
                ),

                   new Recipe
                (
                    "Tagliatelles de concombre au saumon fumé",

                    new Ingredient[6]
                    {
                        new Ingredient(" 2 concombres"),
                        new Ingredient(" 200 g de saumon fumé"),
                        new Ingredient("4 cuillères à soupe de crème fraîche"),
                        new Ingredient("ciboulette"),
                         new Ingredient("1 cuillère à café de moutarde"),
                          new Ingredient("sel, poivre")
                    },

                    new RecipeTask[3]
                    {
                        new RecipeTask("A l'aide d'un économe, éplucher en longeur les concombres afin de faire des lanières.°", 5, oven),
                        new RecipeTask("Mélanger crabe, citron, chapelure, herbes", 1, blender),
                        new RecipeTask("Lier le tout avec un oeuf", 1, kitchenKnife)
                    }
                ),

            };
            SetEmployeeConfig(1, 2, 2, 1);
            SetMaterialConfig(1, 2, 2, 1,5) ;


        }
        public void SetEmployeeConfig(int chefNumber, int partChefNumber, int clerkNumber, int washerNumber)
        {
            chefs = new Chef[chefNumber];
            partChefs = new PartChef[partChefNumber];
            clerks = new KitchenClerk[clerkNumber];
            washers = new Washer[washerNumber];

            for (int i = 0; i < chefs.Length; i++)
            {
                chefs[i] = new Chef();
                chefs[i].y = i;
            }

            for (int i = 0; i < partChefs.Length; i++)
            {
                PartChef partChef = new()
                {
                    x = 1,
                    y = i + 1
                };


                if (i % 2 != 0)
                {
                    partChef.currentSprite = partChef.GetSprite("moving-down");
                }

                partChefs[i] = partChef;
            }

            for (int i = 0; i < clerks.Length; i++)
            {
                clerks[i] = new();
                clerks[i].x = 0;
                clerks[i].y = i + 2;
            }

            for (int i = 0; i < washers.Length; i++)
            {
                washers[i] = new Washer();
            }
        }

        public void SetMaterialConfig(int cookingFireNumber, int ovenNumber, int blenderNumber, int panNumber, int kitchenKnifeNumber)
        {
            cookingFire.quantity = cookingFireNumber;
            oven.quantity = ovenNumber;
            blender.quantity = blenderNumber;
            pan.quantity = panNumber;
            kitchenKnife.quantity = kitchenKnifeNumber;

        }
        public void AddObserver(IObserver observer)
        {
            observers.Add(observer);
        }

        public void NotifyHasMoved(int pastX, int pastY, int newX, int newY)
        {
            foreach (IObserver observer in observers)
            {
                observer.UpdateHasMoved(pastX, pastY, newX, newY);
            }
        }

        public void NotifyMaterialChange(String name)
        {
            foreach (IObserver observer in observers)
            {
                observer.UpdateMaterialChange(name);
            }
        }

        public void NotifyTaskEmployeeChange(String name)
        {
            foreach (IObserver observer in observers)
            {
                //observer.UpdateMaterialChange(name);
            }
        }

        public void NotifyFreeEmployee(String name)
        {
            foreach (IObserver observer in observers)
            {
                observer.UpdateFreeEmployee(name);
            }
        }

        public void NotifyBusyEmloyee(String name)
        {
            foreach (IObserver observer in observers)
            {
                observer.UpdateBusyEmployee(name);
            }
        }

        public void NotifyEventLog(String eventName)
        {
            foreach (IObserver observer in observers)
            {
                observer.UpdateEventLog(eventName);
            }
        }
    }


}
