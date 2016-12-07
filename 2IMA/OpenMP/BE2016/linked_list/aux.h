
struct node{
  unsigned long val;
  struct node *next;
};


long usecs ();
unsigned long process_node(struct node *node);
void mysleep(double sec);
void init_list(struct node **head);
