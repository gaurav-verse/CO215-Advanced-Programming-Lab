from typing import List, Dict, Set
from collections import defaultdict
from functools import reduce


# 1. Total Time Per User
def total_time_per_user(logs: List[Dict]) -> Dict[str, float]:

    user_time = defaultdict(float)
    def reducer(acc, log):
        acc[log["user"]] += log["duration"]
        return acc

    return dict(reduce(reducer, logs, user_time))


# 2. Most Active Users
def most_active_users(logs: List[Dict], k: int) -> List[str]:

    user_time = total_time_per_user(logs)
    sorted_users = sorted(
        user_time.items(),
        key=lambda x: x[1],
        reverse=True
    )

    return [user for user, _ in sorted_users[:k]]


# 3. Unique Actions
def unique_actions(logs: List[Dict]) -> Set[str]:

    return {log["action"] for log in logs}


#Data
logs = [
    {"user": "Ram", "action": "YouTube", "duration": 25.5},
    {"user": "Mohan", "action": "Instagram", "duration": 10.0},
    {"user": "Mohan", "action": "WhatsApp", "duration": 5.0},
    {"user": "Ram", "action": "YouTube", "duration": 40.0},
    {"user": "Akash", "action": "Facebook", "duration": 15.0},
]


print("Total Time Per User:")
print(total_time_per_user(logs))

print("\nTop 2 Most Active Users:")
print(most_active_users(logs, 2))

print("\nUnique Actions:")
print(unique_actions(logs))