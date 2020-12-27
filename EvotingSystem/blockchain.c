#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <openssl/sha.h>
struct block{
	unsigned char prevHash[SHA256_DIGEST_LENGTH];
	char id[13];
	int option;
	struct block *next;
}*head;

void createBlock(char* id,int data);
int verifyId(char* inputid);
int verifyOption(int option);
int DuplicateVoting(char* inputid);
void verifyIntegrity();
int hashCheck(unsigned char *str1,unsigned char *str2);
unsigned char* toString(struct block b);
void viewAll();
void Result();

void main(){
	int choice,option,r;
	char id[13];
	printf("Choices: 1)Vote\t2)verify Integrity\t3)view All\t4)Result\t5)Exit\n");
	while(1){
		printf("Enter your Choice:");
		scanf("%d",&choice);
		switch(choice){
			case 1:
				printf("Valid Voting Options are:1,2,3,4\n");
				printf("EnterId:");
				scanf("%s",id);
				if (!verifyId(id)){
					printf("Id is not valid!\n");
					break;
				}
				if(DuplicateVoting(id)){
					printf("Vote already casted!\n");
					continue;
				}
				printf("Vote:");
				scanf("%d",&option);
				if (!verifyOption(option))
				{
					printf("Voting Option not valid!\n");
					continue;
				}
				createBlock(id,option);
				break;
			case 2:
				verifyIntegrity();
				break;
			case 3:
				viewAll();
				break;
			case 4:
				Result();
				break;
			case 5:
				return;
				break;
			default:
				printf("Invalid Choice,Try again\n");
				break;
		}
	}
}

//SHA256(src,size,dest)
//src and dest are of type unsigned char *
void createBlock(char* id,int data){
	if (head==NULL)
	{
		head=malloc(sizeof(struct block));
		SHA256("",sizeof(""),head->prevHash);
		for (int i = 0; i < 13; ++i)
		{
			head->id[i] = id[i];
		}
		head->option = data;
	}
	else{
		struct block *currentBlock = head;
		while(currentBlock->next)
			currentBlock = currentBlock->next;
		struct block *newBlock = malloc(sizeof(struct block));
		currentBlock->next = newBlock;
		for (int i = 0; i < 13; ++i)
		{
			newBlock->id[i] = id[i];
		}
		newBlock->option = data;
		SHA256(toString(*currentBlock),sizeof(*currentBlock),newBlock->prevHash);
	}
}
int verifyId(char* inputid){
	int valid=0;
	char validid[10][13]={
	{'2','0','1','7','B','4','A','7','0','5','8','0','H'},
	{'2','0','1','7','B','4','A','7','0','5','8','1','H'},
	{'2','0','1','7','B','4','A','7','0','5','8','2','H'},
	{'2','0','1','7','B','4','A','7','0','5','8','3','H'},
	{'2','0','1','7','B','4','A','7','0','5','8','4','H'},
	{'2','0','1','7','B','4','A','7','0','5','8','5','H'},
	{'2','0','1','7','B','4','A','7','0','5','8','6','H'},
	{'2','0','1','7','B','4','A','7','0','5','8','7','H'},
	{'2','0','1','7','B','4','A','7','0','5','8','8','H'},
	{'2','0','1','7','B','4','A','7','0','5','8','9','H'}
	};
	for (int i = 0; i < 10; ++i)
	{
		int equal=1;
		for (int j = 0; j < 13; ++j)
		{
			if (inputid[j] != validid[i][j])
			{
				equal=0;
			}
		}
		if(equal==1) valid=1;
	}
	return valid;
}
int verifyOption(int option){
	if(option>0 && option<5) return 1;
	else return 0;
}
int DuplicateVoting(char* inputid){
	int duplicate=0;
	struct block *curr = head;
	while(curr){
		int equal=1;
		for (int i = 0; i < 13; ++i)
		{
			if (curr->id[i] != inputid[i])
			{
				equal=0;
			}
		}
		if(equal==1) duplicate=1;
		curr = curr->next;
	}
	return duplicate;
}
void verifyIntegrity(){
	int verified=0;
	if (head==NULL)
	{
		printf("No Blocks Found . Try again after adding some blocks");
	}
	else{
		struct block *curr = head->next,*prev=head;
		int alwaysSameHash=1; 
		while(curr){
			if(!hashCheck(SHA256(toString(*prev),sizeof(*prev),NULL),curr->prevHash)){
			alwaysSameHash=0;
			prev=curr;
			curr=curr->next;
			}
		}
		if (alwaysSameHash)
		{
			verified=1;
		}
	}
	if(verified==0) printf("Not Verified!\n");
	else printf("Verified!\n");
}

int hashCheck(unsigned char *str1,unsigned char *str2){ //returns 1 if same hash
	int same=1;
	for (int i = 0; i < SHA256_DIGEST_LENGTH; ++i)
	{
		if (str1[i]!=str2[i])
			same=0;
	}
	return same;
}

unsigned char* toString(struct block b){ //converts a struct to string
	unsigned char *str=malloc(sizeof(unsigned char)*sizeof(b));
	memcpy(str,&b,sizeof(b));
	return str;
}

void viewAll(){
	struct block *curr=head;
	int count=0;
	while(curr){
		 printf("%s\n", curr->id);
		 curr=curr->next;
	}
}

void Result(){
	int numberofVotes[4]={0,0,0,0};
	struct block *curr=head;
	do{
		switch(curr->option){
			case 1:
				numberofVotes[0]++;
				break;
			case 2:
				numberofVotes[1]++;
				break;
			case 3:
				numberofVotes[2]++;
				break;
			case 4:
				numberofVotes[3]++;
				break;
		}
		curr=curr->next;
	}while(curr);
	printf("Total Number of Votes casted:%d\n", numberofVotes[0]+numberofVotes[1]+numberofVotes[2]+numberofVotes[3]);
	printf("Number of votes for Opt 1:%d\n",numberofVotes[0]);
	printf("Number of votes for Opt 2:%d\n",numberofVotes[1]);
	printf("Number of votes for Opt 3:%d\n",numberofVotes[2]);
	printf("Number of votes for Opt 4:%d\n",numberofVotes[3]);
}
/*
gcc blockchain.c -o blockchain -lcrypto
./blockchain
*/
