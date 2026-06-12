import gc
import sys

# ── Step 1: Node class ────────────────────────────────────────────────────────
class Node:
    def __init__(self, name):
        self.name = name
        self.link = None          # will point to another Node — creating the cycle

    def __repr__(self):
        return f"Node({self.name})"


# ── Step 2: Create the cycle ──────────────────────────────────────────────────
print("=" * 52)
print("   GARBAGE COLLECTOR DEMO — REFERENCE CYCLES")
print("=" * 52)

A = Node("A")
B = Node("B")

A.link = B        # A now references B
B.link = A        # B now references A — cycle created


# ── Step 3: Check reference counts BEFORE deletion ───────────────────────────
print("\n--- BEFORE del A and del B ---")
print(f"Reference count of A: {sys.getrefcount(A)}")
# getrefcount always shows +1 extra because passing A to the function
# itself creates a temporary reference — so real count = shown - 1
print(f"Reference count of B: {sys.getrefcount(B)}")
print(f"  (Note: getrefcount adds 1 for the function call itself)")


# ── Step 4: Delete the variable names ────────────────────────────────────────
print("\n--- DELETING A and B ---")
del A
del B
print("del A and del B executed.")
print("You can no longer access these objects from your code.")
print("But they still reference EACH OTHER internally — so ref count is not zero.")


# ── Step 5: Show objects still exist in memory ────────────────────────────────
print("\n--- INVESTIGATING MEMORY (before gc.collect) ---")

# Disable automatic GC so we can show the cycle is still there
gc.disable()

# gc.get_objects() returns all objects tracked by the garbage collector
all_objects = gc.get_objects()
surviving_nodes = [obj for obj in all_objects if isinstance(obj, Node)]

print(f"Node objects still in memory: {len(surviving_nodes)}")
for node in surviving_nodes:
    print(f"  Found: {node} — still has link to: {node.link}")


# ── Step 6: Force garbage collection ─────────────────────────────────────────
print("\n--- RUNNING gc.collect() ---")
gc.enable()
collected = gc.collect()
print(f"Number of unreachable objects collected: {collected}")


# ── Step 7: Confirm they are gone ─────────────────────────────────────────────
print("\n--- AFTER gc.collect() ---")
all_objects_after = gc.get_objects()
surviving_nodes_after = [obj for obj in all_objects_after if isinstance(obj, Node)]
print(f"Node objects still in memory: {len(surviving_nodes_after)}")
print("Cycle successfully cleaned up by the Garbage Collector.")
print("=" * 52)