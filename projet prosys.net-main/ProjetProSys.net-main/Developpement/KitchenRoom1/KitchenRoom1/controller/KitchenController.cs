using KitchenRoom1.contract;
using KitchenRoom1.model;
using KitchenRoom1.view;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace KitchenRoom1.controller
{
    public class KitchenController
    {
        private static int TIME_SCALE = 1000;

        public KitchenModel model { get; set; }
        public KitchenView view { get; set; }
        public Queue<Order> orderQueue { get; set; }
        private static Mutex orderQueueMut = new Mutex();
        private static ManualResetEvent orderQueueMre = new ManualResetEvent(false);
        private static Mutex notifyOrderQueueMut = new Mutex();
        public Queue<Order> pendingOrderQueue { get; set; }
        private static Mutex pendingOrderQueueMut = new Mutex();
        private static ManualResetEvent pendingOrderQueueMre = new ManualResetEvent(false);
        private static Mutex notifyPendingOrderQueueMut = new Mutex();

        public Queue<Order> doneOrderQueue { get; set; }
        private static Mutex doneOrderQueueMut = new Mutex();
        private static ManualResetEvent doneOrderQueueMre = new ManualResetEvent(false);
        private static Mutex notifyDoneOrderQueueMut = new Mutex();

        public Queue<Recipe> recipeQueue { get; set; }
        private static Mutex recipeQueueMut = new Mutex();
        private static ManualResetEvent recipeQueueMre = new ManualResetEvent(false);
        private static Mutex notifyRecipeQueueMut = new Mutex();

        public Queue<RecipeTask> recipeTaskQueue { get; set; }
        private static Mutex recipeTaskQueueMut = new Mutex();
        private static ManualResetEvent recipeTaskQueueMre = new ManualResetEvent(false);
        private static Mutex notifyRecipeTaskQueueMut = new Mutex();

        public Queue<KitchenMaterial> materialWashQueue { get; set; }
        private static Mutex materialWashQueueMut = new Mutex();
        private static ManualResetEvent materialWashQueueMre = new ManualResetEvent(false);
        private static Mutex notifyMaterialWashMut = new Mutex();



        public KitchenController(KitchenModel model, KitchenView view)
        {
            this.model = model;
            this.view = view;

            orderQueue = new Queue<Order>();
            pendingOrderQueue = new Queue<Order>();
            doneOrderQueue = new Queue<Order>();
            recipeQueue = new Queue<Recipe>();
            recipeTaskQueue = new Queue<RecipeTask>();
            materialWashQueue = new Queue<KitchenMaterial>();
        }

        public void Start()
        {
            Application.Run(view.controlForm);
            model.AddObserver(view);

            for (int i = 0; i < model.chefs.Length; i++)
            {
                Thread ChefThread = new(new ThreadStart(ChefTask));
                ChefThread.Name = "Chef #" + (i + 1);
                ChefThread.Start();
            }

            Thread distantWaiterThread = new(new ThreadStart(DistantWaiterTask));
            distantWaiterThread.Name = "Waiter";
            distantWaiterThread.Start();

            for (int i = 0; i < model.partChefs.Length; i++)
            {
                Thread partChefThread = new(new ThreadStart(PartChefTask));
                partChefThread.Name = "Part Chef #" + (i + 1);
                partChefThread.Start();
            }

            for (int i = 0; i < model.clerks.Length; i++)
            {
                Thread KitchenClerkThread = new(new ThreadStart(KitchenClerkTask));
                KitchenClerkThread.Name = "Clerk #" + (i + 1);
                KitchenClerkThread.Start();
            }


            for (int i = 0; i < model.washers.Length; i++)
            {
                Thread washerThread = new(new ThreadStart(WasherTask));
                washerThread.Name = "Washer #" + (i + 1);
                washerThread.Start();
            }

            Application.Run(view.kitchenForm);
        }

        private void ChefTask()
        {
            while (true)
            {
                Console.WriteLine(Thread.CurrentThread.Name + ": Waiting for order...");

                notifyOrderQueueMut.WaitOne();
                orderQueueMre.WaitOne();
                orderQueueMre.Reset();
                notifyOrderQueueMut.ReleaseMutex();
                Console.WriteLine(Thread.CurrentThread.Name + ": Order received.");
                Console.WriteLine(Thread.CurrentThread.Name + ": Sending to Part Chef");
                //Changing sprite
                int threadNumber = int.Parse((Thread.CurrentThread.Name[Thread.CurrentThread.Name.Length - 1]) + "");
                int chefPosition = threadNumber - 1;
                model.chefs[chefPosition].currentSprite = model.chefs[chefPosition].GetSprite("ready");
                model.NotifyHasMoved(model.chefs[chefPosition].x, model.chefs[chefPosition].y, model.chefs[chefPosition].x, model.chefs[chefPosition].y);

                orderQueueMut.WaitOne();
                pendingOrderQueueMut.WaitOne();
                pendingOrderQueue.Enqueue(orderQueue.First<Order>());
                pendingOrderQueueMre.Set();
                pendingOrderQueueMut.ReleaseMutex();

                recipeQueueMut.WaitOne();
                recipeQueue.Enqueue(orderQueue.First<Order>().recipe);
                Thread.Sleep(2000);
                recipeQueueMre.Set();
                recipeQueueMut.ReleaseMutex();
                orderQueue.Dequeue();

                orderQueueMut.ReleaseMutex();



                //Changing sprite
                model.chefs[chefPosition].currentSprite = model.chefs[chefPosition].GetSprite("waiting");
                model.NotifyHasMoved(model.chefs[chefPosition].x, model.chefs[chefPosition].y, model.chefs[chefPosition].x, model.chefs[chefPosition].y);

            }
        }

        private void PartChefTask()
        {
            while (true)
            {
                Console.WriteLine(Thread.CurrentThread.Name + ": Waiting for Recipe...");
                notifyOrderQueueMut.WaitOne();
                recipeQueueMre.WaitOne();
                recipeQueueMre.Reset();


                notifyOrderQueueMut.ReleaseMutex();


                Console.WriteLine(Thread.CurrentThread.Name + ": Recipe received.");

                recipeQueueMut.WaitOne();
                Recipe recipe = recipeQueue.First<Recipe>();
                recipeQueue.Dequeue();
                recipeQueueMut.ReleaseMutex();

                int threadNumber = int.Parse((Thread.CurrentThread.Name[Thread.CurrentThread.Name.Length - 1]) + "");
                int partChefPosition = threadNumber - 1;
                model.partChefs[partChefPosition].currentSprite = model.partChefs[partChefPosition].GetSprite("moving-right");
                model.NotifyHasMoved(model.partChefs[partChefPosition].x, model.partChefs[partChefPosition].y, model.partChefs[partChefPosition].x, model.partChefs[partChefPosition].y);

                for (int i = 0; i <= 4; i++)
                {
                    int pastX = model.partChefs[partChefPosition].x;
                    int pastY = model.partChefs[partChefPosition].y;
                    model.partChefs[partChefPosition].MoveRight();
                    model.NotifyHasMoved(pastX, pastY, model.partChefs[partChefPosition].x, model.partChefs[partChefPosition].y);
                }

                if (partChefPosition % 2 == 0)
                    model.partChefs[partChefPosition].currentSprite = model.partChefs[partChefPosition].GetSprite("moving-up");
                else
                    model.partChefs[partChefPosition].currentSprite = model.partChefs[partChefPosition].GetSprite("moving-down");

                model.NotifyHasMoved(model.partChefs[partChefPosition].x, model.partChefs[partChefPosition].y, model.partChefs[partChefPosition].x, model.partChefs[partChefPosition].y);



                foreach (RecipeTask task in recipe.recipeTasks)
                {
                    while (task.material.quantity == 0)
                    {
                        Console.WriteLine(Thread.CurrentThread.Name + ": Waiting for " + task.material.name);
                        Thread.Sleep(2000);

                    }

                    //foreach(Commodity commodity in recipe.commodities)
                    //{
                    //    commodityQueueMut.WaitOne();
                    //    commodityQueue.Enqueue(commodity);
                    //    commodityQueueMre.Set();
                    //    commodityQueueMut.ReleaseMutex();
                    //}

                    task.material.quantity--;
                    Console.WriteLine(Thread.CurrentThread.Name + ": Doing task '" + task.name + "' ...");
                    Thread.Sleep(task.duration * TIME_SCALE);
                    Console.WriteLine(Thread.CurrentThread.Name + ": Task '" + task.name + "' done.");
                    //task.material = (task.material.Item1, task.material.Item2 + 1);
                    materialWashQueueMut.WaitOne();
                    if (task.material.washable)
                    {
                        materialWashQueue.Enqueue(task.material);
                        materialWashQueueMre.Set();
                    }
                    else
                        task.material.quantity++;
                    materialWashQueueMut.ReleaseMutex();
                }

                Console.WriteLine(Thread.CurrentThread.Name + ": Recipe '" + recipe.name + "' done.");
                model.partChefs[partChefPosition].currentSprite = model.partChefs[partChefPosition].GetSprite("moving-left");

                for (int i = 0; i <= 4; i++)
                {
                    int pastX = model.partChefs[partChefPosition].x;
                    int pastY = model.partChefs[partChefPosition].y;
                    model.partChefs[partChefPosition].MoveLeft();
                    model.NotifyHasMoved(pastX, pastY, model.partChefs[partChefPosition].x, model.partChefs[partChefPosition].y);
                }

                if (partChefPosition % 2 == 0)
                    model.partChefs[partChefPosition].currentSprite = model.partChefs[partChefPosition].GetSprite("moving-up");
                else
                    model.partChefs[partChefPosition].currentSprite = model.partChefs[partChefPosition].GetSprite("moving-down");

                model.NotifyHasMoved(model.partChefs[partChefPosition].x, model.partChefs[partChefPosition].y, model.partChefs[partChefPosition].x, model.partChefs[partChefPosition].y);


                doneOrderQueueMut.WaitOne();
                pendingOrderQueueMut.WaitOne();
                doneOrderQueue.Enqueue(pendingOrderQueue.First<Order>());
                doneOrderQueueMre.Set();
                pendingOrderQueueMut.ReleaseMutex();
                doneOrderQueueMut.ReleaseMutex();
            }
        }

        private void KitchenClerkTask()
        {
            while (true)
            {
                Console.WriteLine(Thread.CurrentThread.Name + ": Waiting for done orders...");

                notifyDoneOrderQueueMut.WaitOne();

                if (doneOrderQueueMre.WaitOne(/*2000*/))
                {
                    doneOrderQueueMre.Reset();
                    notifyDoneOrderQueueMut.ReleaseMutex();

                    Console.WriteLine(Thread.CurrentThread.Name + ": Done order received");

                    int threadNumber = int.Parse((Thread.CurrentThread.Name[Thread.CurrentThread.Name.Length - 1]) + "");
                    int clerkPosition = threadNumber - 1;
                    model.clerks[clerkPosition].currentSprite = model.clerks[clerkPosition].GetSprite("moving-left");
                    model.NotifyHasMoved(model.clerks[clerkPosition].x, model.clerks[clerkPosition].y, model.clerks[clerkPosition].x, model.clerks[clerkPosition].y);

                    for (int i = 0; i <= 4; i++)
                    {
                        int pastX = model.clerks[clerkPosition].x;
                        int pastY = model.clerks[clerkPosition].y;
                        model.clerks[clerkPosition].MoveLeft();
                        model.NotifyHasMoved(pastX, pastY, model.clerks[clerkPosition].x, model.clerks[clerkPosition].y);
                    }

                    doneOrderQueueMut.WaitOne();
                    Console.WriteLine(Thread.CurrentThread.Name + ": Moving'" + doneOrderQueue.First<Order>().recipe.name + "' to comptoir");
                    doneOrderQueue.Dequeue();
                    doneOrderQueueMut.ReleaseMutex();

                    model.clerks[clerkPosition].currentSprite = model.clerks[clerkPosition].GetSprite("moving-right");
                    model.NotifyHasMoved(model.clerks[clerkPosition].x, model.clerks[clerkPosition].y, model.clerks[clerkPosition].x, model.clerks[clerkPosition].y);

                    for (int i = 0; i <= 4; i++)
                    {
                        int pastX = model.clerks[clerkPosition].x;
                        int pastY = model.clerks[clerkPosition].y;
                        model.clerks[clerkPosition].MoveRight();
                        model.NotifyHasMoved(pastX, pastY, model.clerks[clerkPosition].x, model.clerks[clerkPosition].y);
                    }

                    model.clerks[clerkPosition].currentSprite = model.clerks[clerkPosition].GetSprite("moving-down");
                    model.NotifyHasMoved(model.clerks[clerkPosition].x, model.clerks[clerkPosition].y, model.clerks[clerkPosition].x, model.clerks[clerkPosition].y);
                }

                //notifyCommodityQueueMut.WaitOne();
                //if (commodityQueueMre.WaitOne(2000))
                //{
                //    commodityQueueMre.Reset();
                //    notifyCommodityQueueMut.ReleaseMutex();

                //    Console.WriteLine(Thread.CurrentThread.Name + ": Getting '" + commodityQueue.First<Commodity>().name + "' ...");
                //    Thread.Sleep(500);
                //    Console.WriteLine(Thread.CurrentThread.Name + ": Found '" + commodityQueue.First<Commodity>().name + "'");

                //}

            }
        }

        private void WasherTask()
        {
            while (true)
            {
                Console.WriteLine(Thread.CurrentThread.Name + ": Waiting for material to wash...");
                notifyMaterialWashMut.WaitOne();
                materialWashQueueMre.WaitOne();


                int threadNumber = int.Parse((Thread.CurrentThread.Name[Thread.CurrentThread.Name.Length - 1]) + "");
                int washerPosition = threadNumber - 1;
                model.washers[washerPosition].currentSprite = model.washers[washerPosition].GetSprite("moving-right");
                model.NotifyHasMoved(model.washers[washerPosition].x, model.washers[washerPosition].y, model.washers[washerPosition].x, model.washers[washerPosition].y);

                materialWashQueueMut.WaitOne();
                Console.WriteLine(Thread.CurrentThread.Name + ": Material '" + materialWashQueue.First<KitchenMaterial>().name + "' received.");
                Console.WriteLine(Thread.CurrentThread.Name + ": Washing Material '" + materialWashQueue.First<KitchenMaterial>().name + "' ...");
                Thread.Sleep(2000);
                Console.WriteLine(Thread.CurrentThread.Name + ": Washing Material '" + materialWashQueue.First<KitchenMaterial>().name + "' done.");

                materialWashQueue.First<KitchenMaterial>().quantity++;

                materialWashQueue.Dequeue();

                model.washers[washerPosition].currentSprite = model.washers[washerPosition].GetSprite("moving-left");
                model.NotifyHasMoved(model.washers[washerPosition].x, model.washers[washerPosition].y, model.washers[washerPosition].x, model.washers[washerPosition].y);

                materialWashQueueMut.ReleaseMutex();
                materialWashQueueMre.Reset();
                notifyMaterialWashMut.ReleaseMutex();

            }
        }

        private void DistantWaiterTask()
        {
            while (true)
            {
                Thread.Sleep(5000);
                orderQueueMut.WaitOne();
                orderQueue.Enqueue(new Order(1, model.recipes[0]));
                orderQueueMre.Set();
                orderQueueMut.ReleaseMutex();
            }
        }
    }
}
